package com.example.wisatakalsel.injection

import com.example.wisatakalsel.repo.TourRepository

object Injection {
    fun provideRepository(): TourRepository {
        return TourRepository.getInstance()
    }
}