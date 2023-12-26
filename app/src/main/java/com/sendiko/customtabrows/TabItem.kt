package com.sendiko.customtabrows

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val activeIcon: ImageVector,
    val unActiveIcon: ImageVector,
    val title: String,
)
