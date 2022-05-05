package am.acba.domain.repositories

import am.acba.domain.models.RatesDomainModel
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    suspend fun getRates(): Flow<RatesDomainModel>
}