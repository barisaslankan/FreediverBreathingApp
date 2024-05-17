package com.barisaslankan.freedivingbreathing.presentation.screen.exercise

data class ExerciseState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val currentTime : Long = 0L,
    val isTimerRunning : Boolean = false,
    val note : String = ""
)
