package com.brainrot.reflex.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val BrainrotColorScheme = darkColorScheme(
    primary = NeonGreen,
    secondary = NeonPink,
    tertiary = NeonYellow,
    background = BrainrotBlack,
    surface = BrainrotSurface,
    onPrimary = BrainrotBlack,
    onSecondary = BrainrotBlack,
    onTertiary = BrainrotBlack,
    onBackground = NeonGreen,
    onSurface = NeonGreen
)

@Composable
fun BrainrotReflexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BrainrotColorScheme,
        typography = Typography,
        content = content
    )
}
