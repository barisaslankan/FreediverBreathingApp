package com.barisaslankan.freedivingbreathing.presentation.screen.exercise

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ExerciseScreen(
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            ExerciseScreenContent(
                currentTime = state.currentTime,
                onNoteTextChanged = { viewModel.onNoteTextChanged(it) },
                onRecordSaved = { duration, note -> viewModel.insertRecord(duration = duration, note =  note) },
                onTimerStopped = { viewModel.stopTimer() },
                onTimerStarted = { viewModel.startTimer() },
                onTimerPaused = { viewModel.pauseTimer() },
                isTimerRunning = state.isTimerRunning
            )
        }
    }
}
