package com.barisaslankan.freedivingbreathing.presentation.screen.records

import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import java.time.LocalDate

data class RecordsState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val records : Map<LocalDate, List<BreatheRecord>>? = null
)