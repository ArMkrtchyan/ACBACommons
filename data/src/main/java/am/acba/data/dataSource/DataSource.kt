package am.acba.data.dataSource

import am.acba.acbacommons.exceptions.AuthException
import am.acba.acbacommons.exceptions.BadRequestException
import am.acba.acbacommons.exceptions.InternalServerErrorException
import am.acba.acbacommons.exceptions.NetworkException
import am.acba.acbacommons.shared.NetworkConnection
import am.acba.acbacommons.shared.PreferencesManager
import am.acba.data.mappers.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DataSource constructor(
    private val mPreferencesManager: PreferencesManager,
    private val mNetworkConnection: NetworkConnection,
) : IDataSource {

    override suspend fun <T, K> getResult(mapper: IMapper<T, K>?, call: suspend () -> ResponseResult<T>): Flow<K> {
        return flow {
            if (!mNetworkConnection.networkStateFlow.value) throw NetworkException("No internet connection.")
            val result = call()
            if (result.isSuccessful) {
                emit(result.body()?.Result?.let { mapper?.map(it) } ?: throw InternalServerErrorException(result.message()))
            } else {
                if (result.code() == 401) {
                    throw AuthException()
                }
                if (result.code() == 404) throw InternalServerErrorException(result.message())
                if (result.code() in 400..499 && result.code() != 401 && result.code() != 404) throw BadRequestException(result.message())
                else if (result.code() in 500..599) throw InternalServerErrorException(result.message())
            }
        }.catch {
            throw if (it is SocketTimeoutException || it is UnknownHostException) NetworkException(it.localizedMessage ?: it.message ?: "") else it
        }.flowOn(Dispatchers.IO)
    }
}