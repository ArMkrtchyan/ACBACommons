package com.github.armkrtchyan.data.services

import com.github.armkrtchyan.common.retrofit.ResponseResult
import com.github.armkrtchyan.data.responseModel.RatesResponseModel
import retrofit2.http.GET

interface IRatesService {
    @GET("en/v2/rates")
    suspend fun getRates(): ResponseResult<RatesResponseModel>
}