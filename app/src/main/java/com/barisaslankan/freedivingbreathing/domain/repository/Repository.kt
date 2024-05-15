package com.barisaslankan.freedivingbreathing.domain.repository

import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import com.barisaslankan.freedivingbreathing.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias Records = Resource<Map<LocalDate, List<BreatheRecord>>>

interface Repository {
    fun getAllRecords() : Flow<Records>
    suspend fun insertRecord(record : BreatheRecord) : Resource<BreatheRecord>
    suspend fun deleteRecord(record : BreatheRecord) : Resource<Boolean>
    suspend fun updateRecord (record : BreatheRecord) : Resource<BreatheRecord>
}