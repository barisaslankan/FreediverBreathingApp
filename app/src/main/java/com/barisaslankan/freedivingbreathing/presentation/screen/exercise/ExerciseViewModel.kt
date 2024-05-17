package com.barisaslankan.freedivingbreathing.presentation.screen.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import com.barisaslankan.freedivingbreathing.domain.repository.Repository
import com.barisaslankan.freedivingbreathing.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(ExerciseState())
    val state = _state.asStateFlow()

    private val intervalInMillis : Long = 1000L

    private var job : Job? = null

    fun insertRecord(
        note: String,
        duration : Long,
    ){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val record = BreatheRecord()
            record.note = note
            record.duration = duration
            val result = repository.insertRecord(record)
            when(result){
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoading = false, error = null)
                    }
                }
            }
            job?.cancel()
        }
    }

    fun startTimer(){
        job = CoroutineScope(Dispatchers.Default).launch{
                delay(intervalInMillis)
                _state.update {
                    it.copy(currentTime = it.currentTime + intervalInMillis, isTimerRunning = true)
                }
        }
    }

    fun pauseTimer(){
        job?.cancel()
        _state.update {
            it.copy(isTimerRunning = false)
        }
    }

    fun stopTimer(){
        job?.cancel()
        _state.update {
            it.copy(currentTime = 0L, isTimerRunning = false)
        }
    }

    fun onNoteTextChanged(note : String){
        _state.update {
            it.copy(note = note)
        }
    }

}