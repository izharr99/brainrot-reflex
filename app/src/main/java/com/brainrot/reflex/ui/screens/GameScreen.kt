package com.brainrot.reflex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brainrot.reflex.game.GameEngine
import com.brainrot.reflex.game.models.GameState
import com.brainrot.reflex.ui.theme.BrainrotBlack
import com.brainrot.reflex.ui.theme.NeonBlue
import com.brainrot.reflex.ui.theme.NeonGreen
import com.brainrot.reflex.ui.theme.NeonPink
import com.brainrot.reflex.ui.theme.NeonYellow

@Composable
fun GameScreen(
    onGameOver: (score: Int) -> Unit,
    engine: GameEngine = viewModel()
) {
    LaunchedEffect(Unit) {
        engine.startGame()
    }

    LaunchedEffect(engine.gameState) {
        val state = engine.gameState
        if (state is GameState.GameOver) {
            onGameOver(state.score)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrainrotBlack)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "LIVES: ${engine.lives}",
                style = MaterialTheme.typography.titleLarge,
                color = NeonPink
            )
            Text(
                text = "SCORE: ${engine.score}",
                style = MaterialTheme.typography.titleLarge,
                color = NeonGreen
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = engine.gameState) {
                is GameState.Idle -> Unit

                is GameState.CountDown -> {
                    Text(
                        text = "${state.value}",
                        style = MaterialTheme.typography.displayLarge,
                        color = NeonYellow
                    )
                }

                is GameState.ChallengePlaying -> {
                    Text(
                        text = "CHALLENGE #${state.challengeNumber}\n(placeholder)",
                        style = MaterialTheme.typography.headlineLarge,
                        color = NeonYellow
                    )
                    Row(modifier = Modifier.padding(top = 32.dp)) {
                        Button(
                            onClick = { engine.onChallengeResult(success = true) },
                            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
                            modifier = Modifier.padding(end = 12.dp)
                        ) {
                            Text("SUCCEED", color = BrainrotBlack)
                        }
                        Button(
                            onClick = { engine.onChallengeResult(success = false) },
                            colors = ButtonDefaults.buttonColors(containerColor = NeonPink)
                        ) {
                            Text("FAIL", color = BrainrotBlack)
                        }
                    }
                }

                is GameState.ChallengeFeedback -> {
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.headlineLarge,
                        color = if (state.success) NeonGreen else NeonPink
                    )
                }

                is GameState.GameOver -> {
                    Text(
                        text = "GAME OVER",
                        style = MaterialTheme.typography.headlineLarge,
                        color = NeonBlue
                    )
                }
            }
        }
    }
}
