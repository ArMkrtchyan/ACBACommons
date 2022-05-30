package am.acba.data

import am.acba.acbacommons.retrofit.ResponseResult
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun <T, K> getResult(mapper: IMapper<T, K>? = null, call: suspend () -> ResponseResult<T>): Flow<K>
}