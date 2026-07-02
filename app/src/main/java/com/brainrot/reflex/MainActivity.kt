package com.brainrot.reflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brainrot.reflex.navigation.AppNavigation
import com.brainrot.reflex.ui.theme.BrainrotReflexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrainrotReflexTheme {
                AppNavigation()
            }
        }
    }
}
