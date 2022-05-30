package am.acba.data

import am.acba.acbacommons.retrofit.BaseInterceptorsHelper
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class InterceptorHelper : BaseInterceptorsHelper() {
    override val interceptors: List<Interceptor>
        get() = listOf(authInterceptor(), httpLoggerInterceptor)

    private fun authInterceptor(): Interceptor = Interceptor {
        val requestBuilder = it.request()
            .newBuilder()
            .header("Content-Type", "application/json")
            .removeHeader("Pragma")
        val request = requestBuilder.build()
        it.proceed(request)
    }

    private val httpLoggerInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}