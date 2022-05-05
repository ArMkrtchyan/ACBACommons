package am.acba.data.dataSource

import am.acba.data.mappers.IMapper
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun <T, K> getResult(mapper: IMapper<T, K>? = null, call: suspend () -> ResponseResult<T>): Flow<K>
}