package com.sendiko.customtabrows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sendiko.customtabrows.ui.HomeScreen
import com.sendiko.customtabrows.ui.theme.CustomTabRowsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTabRowsTheme {
                val tabs = listOf(
                    TabItem(
                        activeIcon = Icons.Default.Home,
                        unActiveIcon = Icons.Outlined.Home,
                        title = "Dashboard"
                    ),
                    TabItem(
                        activeIcon = Icons.Default.Email,
                        unActiveIcon = Icons.Outlined.Email,
                        title = "Messages"
                    ),
                    TabItem(
                        activeIcon = Icons.Default.Call,
                        unActiveIcon = Icons.Outlined.Call,
                        title = "Call"
                    ),
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(tabs)
                }
            }
        }
    }
}