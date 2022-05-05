package am.acba.acbacommons.exceptions

class BadRequestException(val mMessage: String?) : Exception(mMessage) {}