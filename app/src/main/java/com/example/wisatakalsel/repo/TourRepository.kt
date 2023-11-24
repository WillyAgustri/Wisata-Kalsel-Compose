package com.example.wisatakalsel.repo

import com.example.wisatakalsel.model.Tour
import com.example.wisatakalsel.model.TourData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class TourRepository {
    private val dummyTour = mutableListOf<Tour>()

    init {
        if (dummyTour.isEmpty()) {
            TourData.dummyTour.forEach {
                dummyTour.add(it)
            }
        }
    }

    fun getTourById(tourId: Int): Tour {
        return dummyTour.first {
            it.id == tourId
        }
    }

    fun getFavoriteTour(): Flow<List<Tour>> {
        return flowOf(dummyTour.filter { it.isFavorite })
    }

    fun searchTour(query: String) = flow {
        val data = dummyTour.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateTour(tourId: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyTour.indexOfFirst { it.id == tourId }
        val result = if (index >= 0) {
            val tour = dummyTour[index]
            dummyTour[index] = tour.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: TourRepository? = null

        fun getInstance(): TourRepository =
            instance ?: synchronized(this) {
                TourRepository().apply {
                    instance = this
                }
            }
    }
}