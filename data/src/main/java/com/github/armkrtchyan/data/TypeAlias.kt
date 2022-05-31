package com.github.armkrtchyan.common.retrofit

import com.github.armkrtchyan.data.responseModel.ResponseModel
import retrofit2.Response

typealias ResponseResult<T> = Response<ResponseModel<T>>