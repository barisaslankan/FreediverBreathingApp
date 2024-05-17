package com.barisaslankan.freedivingbreathing.presentation.navigation

sealed class Screen(val route : String) {
    data object ExerciseScreen : Screen("exercise_screen")
    data object ProgressScreen : Screen("progress_screen")
    data object RecordsScreen : Screen("records_screen")
}