package am.acba.acbacommons.retrofit

import am.acba.acbacommons.BuildConfig
import am.acba.acbacommons.shared.PreferencesManager
import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule(
    private val mPreferencesManager: PreferencesManager,
    private val context: Context,
    private val interceptors: BaseInterceptorsHelper,
) {

    private fun provideOkHttpClient(): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
        for (interceptor: Interceptor in interceptors.interceptors) {
            client.addInterceptor(interceptor)
        }
        return client.build()
    }

    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl("https://www.acbadigital.am/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}