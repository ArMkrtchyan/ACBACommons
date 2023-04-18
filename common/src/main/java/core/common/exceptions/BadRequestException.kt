package core.common.exceptions

class BadRequestException(val mMessage: String?) : Exception(mMessage) {}