package com.example.wisatakalsel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wisatakalsel.model.Tour
import com.example.wisatakalsel.repo.TourRepository
import com.example.wisatakalsel.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TourRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Tour>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Tour>>
        get() = _uiState

    fun getTourById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getTourById(id))
    }

    fun updateTour(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateTour(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getTourById(id)
            }
    }

}