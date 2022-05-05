package am.acba.data.services

import retrofit2.Retrofit

object ServiceFactory {

    fun createRatesService(retrofit: Retrofit): IRatesService {
        return retrofit.create(IRatesService::class.java)
    }
}