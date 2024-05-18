package com.barisaslankan.freedivingbreathing.presentation.screen.records

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import com.barisaslankan.freedivingbreathing.presentation.screen.records.component.DateHeader
import com.barisaslankan.freedivingbreathing.presentation.screen.records.component.RecordCard
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreenContent(records : Map<LocalDate, List<BreatheRecord>>? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Records",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            records?.forEach { (date, records) ->
                stickyHeader {
                    DateHeader(localDate = date)
                }
                items(records) { record ->
                    RecordCard(record = record)
                }
            }
        }
    }
}