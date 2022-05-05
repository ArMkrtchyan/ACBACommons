package am.acba.data.dataSource

import am.acba.data.responseModel.ResponseModel
import retrofit2.Response

typealias ResponseResult<T> = Response<ResponseModel<T>>