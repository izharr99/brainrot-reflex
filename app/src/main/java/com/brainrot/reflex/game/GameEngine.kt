package com.brainrot.reflex.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainrot.reflex.game.models.GameState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameEngine : ViewModel() {

    companion object {
        private const val LIVES_TOTAL = 3
        private const val BASE_DURATION_MS = 3000L
        private const val DURATION_FLOOR_MS = 1200L
        private const val SPEED_STEP = 0.05
        private const val CHALLENGES_PER_SPEED_STEP = 5
        private const val COUNTDOWN_START = 3

        private val ROASTS = listOf(
            "your reflexes have left the chat",
            "skill issue fr",
            "bro cooked themselves",
            "L + ratio + no cap",
            "the NPC arc begins now",
            "even grandma faster than u"
        )

        private val HYPE = listOf(
            "W",
            "actual sigma",
            "6 in a row?? touch grass",
            "bro is built different",
            "no way fr"
        )
    }

    var gameState by mutableStateOf<GameState>(GameState.Idle)
        private set

    var lives by mutableIntStateOf(LIVES_TOTAL)
        private set

    var score by mutableIntStateOf(0)
        private set

    var speedMultiplier by mutableStateOf(1.0)
        private set

    private var streak = 0
    private var timeoutJob: Job? = null

    fun startGame() {
        lives = LIVES_TOTAL
        score = 0
        speedMultiplier = 1.0
        streak = 0
        viewModelScope.launch {
            for (value in COUNTDOWN_START downTo 1) {
                gameState = GameState.CountDown(value)
                delay(700)
            }
            startNextChallenge()
        }
    }

    private fun startNextChallenge() {
        timeoutJob?.cancel()
        val duration = currentDurationMs()
        gameState = GameState.ChallengePlaying(challengeNumber = score + 1, durationMs = duration)
        timeoutJob = viewModelScope.launch {
            delay(duration)
            onChallengeResult(success = false)
        }
    }

    fun onChallengeResult(success: Boolean) {
        if (gameState !is GameState.ChallengePlaying) return
        timeoutJob?.cancel()

        if (success) {
            score += 1
            streak += 1
            if (score % CHALLENGES_PER_SPEED_STEP == 0) {
                speedMultiplier += SPEED_STEP
            }
            val message = if (streak >= 3) HYPE.random() else "nice"
            showFeedbackThenContinue(success = true, message = message)
        } else {
            streak = 0
            lives -= 1
            val message = ROASTS.random()
            if (lives <= 0) {
                showFeedbackThenGameOver(message = message)
            } else {
                showFeedbackThenContinue(success = false, message = message)
            }
        }
    }

    private fun showFeedbackThenContinue(success: Boolean, message: String) {
        gameState = GameState.ChallengeFeedback(success = success, message = message)
        viewModelScope.launch {
            delay(600)
            startNextChallenge()
        }
    }

    private fun showFeedbackThenGameOver(message: String) {
        gameState = GameState.ChallengeFeedback(success = false, message = message)
        viewModelScope.launch {
            delay(600)
            gameState = GameState.GameOver(score = score)
        }
    }

    private fun currentDurationMs(): Long {
        val scaled = (BASE_DURATION_MS / speedMultiplier).toLong()
        return scaled.coerceAtLeast(DURATION_FLOOR_MS)
    }

    override fun onCleared() {
        timeoutJob?.cancel()
        super.onCleared()
    }
}
