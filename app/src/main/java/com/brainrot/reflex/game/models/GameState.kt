package com.brainrot.reflex.game.models

sealed class GameState {
    data object Idle : GameState()
    data class CountDown(val value: Int) : GameState()
    data class ChallengePlaying(val challengeNumber: Int, val durationMs: Long) : GameState()
    data class ChallengeFeedback(val success: Boolean, val message: String) : GameState()
    data class GameOver(val score: Int) : GameState()
}
