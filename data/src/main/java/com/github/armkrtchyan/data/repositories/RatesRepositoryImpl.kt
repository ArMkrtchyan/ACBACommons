package com.github.armkrtchyan.data.repositories

import com.github.armkrtchyan.data.IDataSource
import com.github.armkrtchyan.data.mappers.RatesMapper
import com.github.armkrtchyan.data.services.IRatesService
import com.github.armkrtchyan.domain.models.RatesDomainModel
import com.github.armkrtchyan.domain.repositories.RatesRepository
import kotlinx.coroutines.flow.Flow

class RatesRepositoryImpl(
    private val mDataSource: IDataSource,
    private val mService: IRatesService,
) : RatesRepository {
    override suspend fun getRates(): Flow<RatesDomainModel> = mDataSource.getResult(RatesMapper(), mService::getRates)
}