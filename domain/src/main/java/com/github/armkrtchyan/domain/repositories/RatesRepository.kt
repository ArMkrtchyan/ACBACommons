package com.github.armkrtchyan.domain.repositories

import com.github.armkrtchyan.domain.models.RatesDomainModel
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    suspend fun getRates(): Flow<RatesDomainModel>
}