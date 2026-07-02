package com.brainrot.reflex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.brainrot.reflex.ui.screens.GameScreen
import com.brainrot.reflex.ui.screens.HomeScreen
import com.brainrot.reflex.ui.screens.ScoreCardScreen

object Routes {
    const val HOME = "home"
    const val GAME = "game"
    const val SCORE_CARD = "score_card/{score}"

    fun scoreCard(score: Int) = "score_card/$score"
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onStartClick = { navController.navigate(Routes.GAME) }
            )
        }
        composable(Routes.GAME) {
            GameScreen(
                onGameOver = { score ->
                    navController.navigate(Routes.scoreCard(score)) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                }
            )
        }
        composable(
            route = Routes.SCORE_CARD,
            arguments = listOf(navArgument("score") { type = NavType.IntType })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            ScoreCardScreen(
                score = score,
                onPlayAgain = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}
