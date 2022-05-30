package am.acba.acbacommons.retrofit

import am.acba.data.responseModel.ResponseModel
import retrofit2.Response

typealias ResponseResult<T> = Response<ResponseModel<T>>