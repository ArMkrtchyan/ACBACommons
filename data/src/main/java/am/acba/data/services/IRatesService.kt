package am.acba.data.services

import am.acba.data.dataSource.ResponseResult
import am.acba.data.responseModel.RatesResponseModel
import retrofit2.http.GET
import retrofit2.http.Header

interface IRatesService {
    @GET("en/v2/rates")
    suspend fun getRates(): ResponseResult<RatesResponseModel>
}