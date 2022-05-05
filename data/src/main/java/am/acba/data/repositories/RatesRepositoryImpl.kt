package am.acba.data.repositories

import am.acba.data.dataSource.IDataSource
import am.acba.data.mappers.RatesMapper
import am.acba.data.services.IRatesService
import am.acba.domain.models.RatesDomainModel
import am.acba.domain.repositories.RatesRepository
import kotlinx.coroutines.flow.Flow

class RatesRepositoryImpl(
    private val mDataSource: IDataSource,
    private val mService: IRatesService,
) : RatesRepository {
    override suspend fun getRates(): Flow<RatesDomainModel> = mDataSource.getResult(RatesMapper(), mService::getRates)
}