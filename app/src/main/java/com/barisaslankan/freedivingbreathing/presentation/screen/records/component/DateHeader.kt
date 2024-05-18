package com.barisaslankan.freedivingbreathing.presentation.screen.records.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateHeader(localDate: LocalDate) {
    Text(
        text = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        fontSize = 20.sp,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    )
}