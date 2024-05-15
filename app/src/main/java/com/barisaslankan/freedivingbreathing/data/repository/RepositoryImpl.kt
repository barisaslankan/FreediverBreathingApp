package com.barisaslankan.freedivingbreathing.data.repository

import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import com.barisaslankan.freedivingbreathing.domain.repository.Records
import com.barisaslankan.freedivingbreathing.domain.repository.Repository
import com.barisaslankan.freedivingbreathing.util.Resource
import com.barisaslankan.freedivingbreathing.util.toInstant
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val realm : Realm
) : Repository {
    override fun getAllRecords(): Flow<Records> {
        return try {
             realm.query<BreatheRecord>()
                .sort("date", sortOrder = Sort.DESCENDING)
                .asFlow()
                .map { result ->
                    Resource.Success(
                        data = result.list.groupBy {
                            it.date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                    )
                }
        }catch (e : Exception) {
            flow{ emit(Resource.Error(message = e.localizedMessage))}
        }
    }

    override suspend fun insertRecord(record: BreatheRecord): Resource<BreatheRecord> {
        return realm.write {
            try {
                val addedRecord = copyToRealm(record, updatePolicy = UpdatePolicy.ALL)
                Resource.Success(data = addedRecord)
            }catch (e : Exception){
                Resource.Error(message = e.localizedMessage)
            }
        }
    }

    override suspend fun deleteRecord(record: BreatheRecord): Resource<Boolean> {
        return realm.write {
            val recordToBeDeleted = query<BreatheRecord>(query = "_id == $0", record._id)
                .first().find()
            if(recordToBeDeleted != null){
                try {
                    delete(recordToBeDeleted)
                    Resource.Success(data = true)
                }catch (e : Exception){
                    Resource.Error(message = e.localizedMessage)
                }
            }else {
                Resource.Error(message = "Record does not exist.")
            }
        }
    }

    override suspend fun updateRecord(record: BreatheRecord): Resource<BreatheRecord> {
        return realm.write {
            val recordToBeUpdated = query<BreatheRecord>(query = "_id == $0", record._id).first().find()
            if(recordToBeUpdated != null){
                recordToBeUpdated.duration = record.duration
                recordToBeUpdated.note = record.note
                Resource.Success(data = recordToBeUpdated)
            }else {
                Resource.Error(message = "Record does not exist.")
            }
        }
    }
}