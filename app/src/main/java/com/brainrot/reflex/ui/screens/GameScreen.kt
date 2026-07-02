package com.brainrot.reflex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brainrot.reflex.ui.theme.BrainrotBlack
import com.brainrot.reflex.ui.theme.NeonBlue
import com.brainrot.reflex.ui.theme.NeonYellow

// Placeholder screen. Real game loop (GameEngine, challenges) lands in a later phase.
@Composable
fun GameScreen(onGameOver: (score: Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrainrotBlack)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GAME SCREEN\n(coming soon)",
            style = MaterialTheme.typography.headlineLarge,
            color = NeonYellow
        )
        Button(
            onClick = { onGameOver(0) },
            colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
            modifier = Modifier.padding(top = 48.dp)
        ) {
            Text(
                text = "END GAME",
                style = MaterialTheme.typography.labelLarge,
                color = BrainrotBlack
            )
        }
    }
}
