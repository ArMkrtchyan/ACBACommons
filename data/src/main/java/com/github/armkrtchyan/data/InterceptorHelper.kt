package com.github.armkrtchyan.data

import core.common.retrofit.BaseInterceptorsHelper
import core.common.retrofit.HttpLoggingInterceptor
import okhttp3.Interceptor

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