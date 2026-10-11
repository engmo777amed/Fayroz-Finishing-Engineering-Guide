package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DefectSeverity
import com.example.model.InspectionStatus
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight
import com.example.ui.theme.StatusFail
import com.example.ui.theme.StatusNA
import com.example.ui.theme.StatusPass
import com.example.ui.theme.StatusPending

@Composable
fun FayrozTopBar(
    title: String,
    subtitle: String? = null,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    onSearchClick: (() -> Unit)? = null,
    onCalculatorClick: (() -> Unit)? = null,
    onBookmarkClick: (() -> Unit)? = null,
    isBookmarked: Boolean = false,
    onToggleBookmark: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = FayrozNavy,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(44.dp).testTag("top_bar_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = Color.White
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(FayrozNavyCard)
                        .border(1.dp, FayrozTurquoise.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "F",
                        color = FayrozGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (!subtitle.isNullOrBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = FayrozTurquoiseLight,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (onToggleBookmark != null) {
                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier.size(40.dp).testTag("top_bar_bookmark_toggle")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "المفضلة",
                        tint = if (isBookmarked) FayrozGold else Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            if (onSearchClick != null) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.size(40.dp).testTag("top_bar_search_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "بحث",
                        tint = Color.White
                    )
                }
            }

            if (onCalculatorClick != null) {
                IconButton(
                    onClick = onCalculatorClick,
                    modifier = Modifier.size(40.dp).testTag("top_bar_calc_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = "حاسبة التشطيبات",
                        tint = FayrozGold
                    )
                }
            }
        }
    }
}

@Composable
fun StatusBadge(status: InspectionStatus) {
    val (bg, fg, text) = when (status) {
        InspectionStatus.PASSED -> Triple(StatusPass.copy(alpha = 0.15f), StatusPass, "مطابق ✓")
        InspectionStatus.FAILED -> Triple(StatusFail.copy(alpha = 0.15f), StatusFail, "غير مطابق ✗")
        InspectionStatus.PENDING -> Triple(StatusPending.copy(alpha = 0.15f), StatusPending, "لم يُفحص ⏳")
        InspectionStatus.NOT_APPLICABLE -> Triple(StatusNA.copy(alpha = 0.15f), StatusNA, "لا ينطبق –")
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .border(1.dp, fg.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            color = fg,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SeverityBadge(severity: DefectSeverity) {
    val (bg, fg) = when (severity) {
        DefectSeverity.CRITICAL -> Pair(StatusFail.copy(alpha = 0.15f), StatusFail)
        DefectSeverity.MAJOR -> Pair(FayrozGold.copy(alpha = 0.15f), FayrozGold)
        DefectSeverity.MINOR -> Pair(FayrozTurquoise.copy(alpha = 0.15f), FayrozTurquoise)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .border(1.dp, fg.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = severity.titleAr,
            color = fg,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun EgyptianBrandChip(brand: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(FayrozTurquoise.copy(alpha = 0.12f))
            .border(0.8.dp, FayrozTurquoise.copy(alpha = 0.35f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = brand,
            color = FayrozTurquoiseLight,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SectionHeader(title: String, icon: String? = null, count: Int? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(4.dp, 16.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(FayrozGold)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        if (count != null) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$count",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}
