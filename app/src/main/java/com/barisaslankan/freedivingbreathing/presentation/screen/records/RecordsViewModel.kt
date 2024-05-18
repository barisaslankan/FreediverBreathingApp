package com.barisaslankan.freedivingbreathing.presentation.screen.records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.freedivingbreathing.domain.repository.Repository
import com.barisaslankan.freedivingbreathing.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(RecordsState())
    val state = _state.asStateFlow()

    init {
        getRecords()
    }

    private fun getRecords(){
        _state.update {
            it.copy(isLoading = true)
        }
        repository.getAllRecords().onEach { result ->
            when (result){
                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoading = false, records = result.data)
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}