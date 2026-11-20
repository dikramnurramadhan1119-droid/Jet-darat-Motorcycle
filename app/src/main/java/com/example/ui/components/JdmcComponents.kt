package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.R
import com.example.domain.model.BackendStatus
import com.example.domain.model.SubmissionStatus
import com.example.domain.model.VideoPlatform
import com.example.ui.theme.JdmcBrushedSilver
import com.example.ui.theme.JdmcCarbonElevated
import com.example.ui.theme.JdmcCarbonSurface
import com.example.ui.theme.JdmcGoldBorder
import com.example.ui.theme.JdmcGoldContainer
import com.example.ui.theme.JdmcGunmetal
import com.example.ui.theme.JdmcLightYellow
import com.example.ui.theme.JdmcMetallicSilver
import com.example.ui.theme.JdmcObsidianBlack
import com.example.ui.theme.JdmcSteelOutline
import com.example.ui.theme.JdmcWarmGold
import com.example.ui.theme.StatusApprovedContainer
import com.example.ui.theme.StatusApprovedYellow
import com.example.ui.theme.StatusPendingAmber
import com.example.ui.theme.StatusPendingContainer
import com.example.ui.theme.StatusPublishedContainer
import com.example.ui.theme.StatusPublishedYellow
import com.example.ui.theme.StatusRejectedContainer
import com.example.ui.theme.StatusRejectedSilverAmber
import com.example.util.VideoUrlHelper

@Composable
fun JdmcTopHeaderBar(
    backendStatus: BackendStatus,
    onConfigureBackendClick: () -> Unit,
    onOpenAiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = JdmcObsidianBlack,
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(JdmcObsidianBlack),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.jdmc_club_logo),
                            contentDescription = "JDMC Jet Darat Motorcycle Club Logo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column {
                        Text(
                            text = "JDMC DIGITAL HQ",
                            style = MaterialTheme.typography.titleLarge,
                            color = JdmcLightYellow,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "JET DARAT MOTORCYCLE CLUB • SATU MIMPI RP",
                            style = MaterialTheme.typography.labelSmall,
                            color = JdmcGunmetal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        color = JdmcGoldContainer,
                        shape = RoundedCornerShape(999.dp),
                        border = BorderStroke(1.dp, JdmcLightYellow),
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .clickable(onClick = onOpenAiClick)
                            .testTag("open_jdmc_ai_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = "Open JDMC Road AI",
                                tint = JdmcLightYellow,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "AI",
                                style = MaterialTheme.typography.labelSmall,
                                color = JdmcLightYellow,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    BackendStatusPill(
                        status = backendStatus,
                        onClick = onConfigureBackendClick
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                JdmcLightYellow.copy(alpha = 0.75f),
                                JdmcSteelOutline,
                                JdmcObsidianBlack
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun BackendStatusPill(
    status: BackendStatus,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (label, dotColor, icon) = when (status) {
        is BackendStatus.Connected -> Triple("LIVE API", JdmcLightYellow, Icons.Filled.CloudDone)
        is BackendStatus.Checking -> Triple("CHECKING", StatusPendingAmber, Icons.Filled.Sync)
        is BackendStatus.Unconfigured -> Triple("LOCAL HQ", JdmcBrushedSilver, Icons.Filled.CloudOff)
        is BackendStatus.Unreachable -> Triple("OFFLINE", JdmcWarmGold, Icons.Filled.CloudOff)
    }
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = JdmcCarbonElevated,
        border = BorderStroke(1.dp, if (status.isAvailable) JdmcLightYellow.copy(alpha = 0.6f) else JdmcSteelOutline),
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .clickable(onClick = onClick)
            .testTag("backend_status_pill")
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = dotColor,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = JdmcMetallicSilver,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Configure backend endpoint",
                tint = JdmcGunmetal,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun BackendUnavailableBanner(
    reason: String?,
    onConfigureClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = JdmcGoldContainer.copy(alpha = 0.5f),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, JdmcGoldBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Security,
                    contentDescription = "Backend validation required",
                    tint = JdmcLightYellow,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = stringResource(R.string.backend_unavailable_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = JdmcLightYellow
                )
            }
            Text(
                text = reason?.takeIf { it.isNotBlank() } ?: stringResource(R.string.backend_unavailable_message),
                style = MaterialTheme.typography.bodyMedium,
                color = JdmcMetallicSilver
            )
            Text(
                text = "Security Guarantee: Shared access codes, rate limiting, session tokens, Jabatan roster changes, and admin keys are never hardcoded or validated client-side.",
                style = MaterialTheme.typography.bodySmall,
                color = JdmcBrushedSilver
            )
            OutlinedButton(
                onClick = onConfigureClick,
                border = BorderStroke(1.dp, JdmcLightYellow.copy(alpha = 0.7f)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = JdmcLightYellow),
                modifier = Modifier.testTag("configure_backend_button")
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.backend_configure_cta))
            }
        }
    }
}

@Composable
fun RateLimitBanner(
    remainingSeconds: Int,
    modifier: Modifier = Modifier
) {
    if (remainingSeconds <= 0) return
    Surface(
        color = StatusPendingContainer,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, StatusPendingAmber.copy(alpha = 0.6f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LockClock,
                contentDescription = "Rate limit cooldown",
                tint = StatusPendingAmber
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Server Rate Limit Active",
                    style = MaterialTheme.typography.titleSmall,
                    color = StatusPendingAmber
                )
                Text(
                    text = "Too many validation attempts. Retry permitted in ${remainingSeconds}s.",
                    style = MaterialTheme.typography.bodySmall,
                    color = JdmcMetallicSilver
                )
            }
        }
    }
}

@Composable
fun SessionCountdownBanner(
    roleLabel: String,
    remainingSeconds: Long,
    onRefreshCheck: (() -> Unit)? = null,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = JdmcCarbonElevated,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, JdmcGoldBorder.copy(alpha = 0.6f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(JdmcLightYellow)
                )
                Column {
                    Text(
                        text = roleLabel,
                        style = MaterialTheme.typography.titleSmall,
                        color = JdmcLightYellow
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Timer,
                            contentDescription = null,
                            tint = JdmcBrushedSilver,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "Expires in ${VideoUrlHelper.formatRemainingDuration(remainingSeconds)}",
                            style = MaterialTheme.typography.labelSmall,
                            color = JdmcBrushedSilver
                        )
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (onRefreshCheck != null) {
                    OutlinedButton(
                        onClick = onRefreshCheck,
                        border = BorderStroke(1.dp, JdmcSteelOutline),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = JdmcBrushedSilver)
                    ) {
                        Text("Sync", style = MaterialTheme.typography.labelMedium)
                    }
                }
                OutlinedButton(
                    onClick = onLogout,
                    border = BorderStroke(1.dp, JdmcLightYellow),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = JdmcLightYellow),
                    modifier = Modifier.testTag("session_logout_button")
                ) {
                    Text("Logout", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
fun PlatformBadge(
    platform: VideoPlatform,
    modifier: Modifier = Modifier
) {
    Surface(
        color = JdmcCarbonSurface,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, JdmcSteelOutline),
        modifier = modifier
    ) {
        Text(
            text = platform.displayName.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = JdmcBrushedSilver,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun JabatanBadge(
    jabatan: String,
    modifier: Modifier = Modifier
) {
    if (jabatan.isBlank()) return
    Surface(
        color = JdmcGoldContainer,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, JdmcGoldBorder),
        modifier = modifier
    ) {
        Text(
            text = jabatan.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = JdmcLightYellow,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun SubmissionStatusChip(
    status: SubmissionStatus,
    modifier: Modifier = Modifier
) {
    val (bg, fg) = when (status) {
        SubmissionStatus.PENDING_REVIEW -> StatusPendingContainer to StatusPendingAmber
        SubmissionStatus.APPROVED -> StatusApprovedContainer to StatusApprovedYellow
        SubmissionStatus.REJECTED -> StatusRejectedContainer to StatusRejectedSilverAmber
        SubmissionStatus.PUBLISHED -> StatusPublishedContainer to StatusPublishedYellow
    }
    Surface(
        color = bg,
        shape = RoundedCornerShape(999.dp),
        border = BorderStroke(1.dp, fg.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Text(
            text = status.displayName,
            style = MaterialTheme.typography.labelSmall,
            color = fg,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun FeaturedBadge(modifier: Modifier = Modifier) {
    Surface(
        color = JdmcLightYellow,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Verified,
                contentDescription = "Featured",
                tint = JdmcObsidianBlack,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = "FEATURED",
                style = MaterialTheme.typography.labelSmall,
                color = JdmcObsidianBlack,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ClipThumbnailView(
    thumbnailUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(12.dp))
            .background(JdmcObsidianBlack)
    ) {
        if (thumbnailUrl.isNotBlank()) {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = contentDescription,
                placeholder = painterResource(id = R.drawable.img_jdmc_clip_placeholder),
                error = painterResource(id = R.drawable.img_jdmc_clip_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.img_jdmc_clip_placeholder),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
