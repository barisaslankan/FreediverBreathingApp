package com.barisaslankan.freedivingbreathing.presentation.screen.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barisaslankan.freedivingbreathing.presentation.screen.exercise.component.CircularButton
import com.barisaslankan.freedivingbreathing.util.iconMaker
import com.barisaslankan.freedivingbreathing.util.PAUSE_ICON_PATH
import com.barisaslankan.freedivingbreathing.util.STOP_ICON_PATH

@Composable
fun ExerciseScreenContent(
    currentTime: Long,
    onNoteTextChanged: (String) -> Unit,
    onRecordSaved: (duration: Long, note: String) -> Unit,
    onTimerStopped: () -> Unit,
    onTimerStarted: () -> Unit,
    onTimerPaused: () -> Unit,
    isTimerRunning: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { 1f },
                    color = Color.Blue,
                    modifier = Modifier.size(240.dp),
                    strokeWidth = 8.dp,
                )
                Text(
                    text = "%02d:%02d".format(currentTime / 60000, (currentTime % 60000) / 1000),
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(color = Color.Blue),
                    fontSize = 40.sp
                )
            }

            Spacer(Modifier.height(height = 40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularButton(
                    icon = iconMaker(path = PAUSE_ICON_PATH, viewPortSize = 24f, iconSize = 24.dp),
                    contentDescription = "Pause",
                    onClick = onTimerPaused
                )
                CircularButton(
                    icon = Icons.Default.PlayArrow,
                    contentDescription = "Start",
                    onClick = onTimerStarted
                )
                CircularButton(
                    icon = iconMaker(path = STOP_ICON_PATH, viewPortSize = 24f, iconSize = 24.dp),
                    contentDescription = "Stop",
                    onClick = onTimerStopped
                )
            }
        }

        if(!isTimerRunning && currentTime > 0){
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ){
                TextField(
                    value = "",
                    onValueChange = onNoteTextChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    textStyle = TextStyle(color = Color.Black),
                    placeholder = {
                        Text("Add note here...", color = Color.Gray)
                    },
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                Button(
                    onClick = { onRecordSaved },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save Record",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

    }
}


@Preview
@Composable
fun ExerciseScreenContentPreview(){
    ExerciseScreenContent(
        currentTime = 350000L,
        isTimerRunning = false,
        onNoteTextChanged = {},
        onRecordSaved = {duration, note ->  },
        onTimerPaused = {},
        onTimerStarted = {},
        onTimerStopped = {}
    )
}