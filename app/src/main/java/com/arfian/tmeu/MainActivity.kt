package com.arfian.tmeu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.arfian.tmeu.presentation.home.HomeScreen
import com.arfian.tmeu.ui.theme.TMEUTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                TMEUTheme {
                    HomeScreen()
                }
            }
        }
    }
}