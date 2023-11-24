package com.example.wisatakalsel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wisatakalsel.model.Tour
import com.example.wisatakalsel.repo.TourRepository
import com.example.wisatakalsel.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: TourRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Tour>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Tour>>>
        get() = _uiState

    fun getFavoriteTour() = viewModelScope.launch {
        repository.getFavoriteTour()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateTour(id: Int, newState: Boolean) {
        repository.updateTour(id, newState)
        getFavoriteTour()
    }
}