package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.Screen
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun FayrozBottomNav(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("fayroz_bottom_nav"),
        containerColor = FayrozNavy,
        tonalElevation = 6.dp
    ) {
        val isHome = currentScreen is Screen.Home
        val isStages = currentScreen is Screen.CategoriesList || currentScreen is Screen.CategoryDetail || currentScreen is Screen.StageDetail
        val isMaterials = currentScreen is Screen.MaterialsLibrary || currentScreen is Screen.MaterialDetail
        val isDefects = currentScreen is Screen.TroubleshootingList || currentScreen is Screen.DefectDetail

        NavigationBarItem(
            selected = isHome,
            onClick = { onNavigate(Screen.Home) },
            icon = { Icon(Icons.Default.Home, contentDescription = "الرئيسية") },
            label = { Text("الرئيسية", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = FayrozNavy,
                selectedTextColor = FayrozTurquoiseLight,
                indicatorColor = FayrozTurquoise,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            ),
            modifier = Modifier.testTag("nav_item_home")
        )

        NavigationBarItem(
            selected = isStages,
            onClick = { onNavigate(Screen.CategoriesList) },
            icon = { Icon(Icons.Default.Category, contentDescription = "المراحل") },
            label = { Text("المراحل", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = FayrozNavy,
                selectedTextColor = FayrozTurquoiseLight,
                indicatorColor = FayrozTurquoise,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            ),
            modifier = Modifier.testTag("nav_item_stages")
        )

        NavigationBarItem(
            selected = isMaterials,
            onClick = { onNavigate(Screen.MaterialsLibrary) },
            icon = { Icon(Icons.Default.Inventory2, contentDescription = "الخامات") },
            label = { Text("الخامات", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = FayrozNavy,
                selectedTextColor = FayrozTurquoiseLight,
                indicatorColor = FayrozTurquoise,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            ),
            modifier = Modifier.testTag("nav_item_materials")
        )

        NavigationBarItem(
            selected = isDefects,
            onClick = { onNavigate(Screen.TroubleshootingList) },
            icon = { Icon(Icons.Default.Warning, contentDescription = "العيوب") },
            label = { Text("العيوب", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = FayrozNavy,
                selectedTextColor = FayrozTurquoiseLight,
                indicatorColor = FayrozTurquoise,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            ),
            modifier = Modifier.testTag("nav_item_defects")
        )
    }
}
