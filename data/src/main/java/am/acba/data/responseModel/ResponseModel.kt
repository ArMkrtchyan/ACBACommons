package am.acba.data.responseModel

data class ResponseModel<T>(val Description: String, val Result: T?, val ResultCode: Int?, val ResultCodeDescription: String?)
