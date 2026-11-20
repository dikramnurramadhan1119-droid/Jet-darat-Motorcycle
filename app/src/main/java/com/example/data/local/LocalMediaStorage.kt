package com.example.data.local

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.webkit.MimeTypeMap
import com.example.domain.model.LocalMediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

data class ImportedLocalMedia(
    val mediaType: LocalMediaType,
    val savedFileUri: String,
    val thumbnailUri: String
)

class LocalMediaStorage(private val context: Context) {

    private val mediaDir: File
        get() = File(context.filesDir, "jdmc_media").apply { mkdirs() }

    private val thumbDir: File
        get() = File(context.filesDir, "jdmc_thumbs").apply { mkdirs() }

    suspend fun importFromPickerUri(uri: Uri): Result<ImportedLocalMedia> = withContext(Dispatchers.IO) {
        runCatching {
            val resolver = context.contentResolver
            val mimeType = resolver.getType(uri).orEmpty().lowercase()
            val isVideo = mimeType.startsWith("video/")
            val mediaType = if (isVideo) LocalMediaType.VIDEO else LocalMediaType.PHOTO
            val fallbackExt = if (isVideo) "mp4" else "jpg"
            val ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)?.takeIf { it.isNotBlank() }
                ?: fallbackExt

            val fileId = UUID.randomUUID().toString()
            val targetFile = File(mediaDir, "jdmc_${fileId}.$ext")

            val input = resolver.openInputStream(uri)
                ?: throw IllegalStateException("Unable to read selected gallery file.")
            input.use { stream ->
                FileOutputStream(targetFile).use { out ->
                    stream.copyTo(out, bufferSize = 8192)
                }
            }

            val savedMediaUri = Uri.fromFile(targetFile).toString()
            val thumbnailUri = if (isVideo) {
                extractVideoThumbnail(targetFile, fileId) ?: ""
            } else {
                savedMediaUri
            }

            ImportedLocalMedia(
                mediaType = mediaType,
                savedFileUri = savedMediaUri,
                thumbnailUri = thumbnailUri
            )
        }
    }

    private fun extractVideoThumbnail(videoFile: File, fileId: String): String? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(videoFile.absolutePath)
            val bitmap = retriever.getFrameAtTime(1_000_000L, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
                ?: retriever.frameAtTime
            if (bitmap != null) {
                val thumbFile = File(thumbDir, "thumb_${fileId}.jpg")
                FileOutputStream(thumbFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                }
                bitmap.recycle()
                Uri.fromFile(thumbFile).toString()
            } else {
                null
            }
        } catch (_: Throwable) {
            null
        } finally {
            runCatching { retriever.release() }
        }
    }

    fun deleteSavedFiles(vararg uris: String) {
        uris.forEach { raw ->
            if (raw.startsWith("file://")) {
                runCatching {
                    val path = Uri.parse(raw).path ?: return@runCatching
                    val file = File(path)
                    if (file.exists() && file.absolutePath.startsWith(context.filesDir.absolutePath)) {
                        file.delete()
                    }
                }
            }
        }
    }
}
