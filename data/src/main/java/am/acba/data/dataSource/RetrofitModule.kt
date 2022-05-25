package am.acba.data.dataSource

import am.acba.acbacommons.shared.PreferencesManager
import am.acba.data.BuildConfig
import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule(
    private val mPreferencesManager: PreferencesManager,
    private val context: Context
) {

    private val httpLoggerInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun providesAuthInterceptor(): Interceptor = Interceptor {
        val requestBuilder = it.request()
            .newBuilder()
            .header("Content-Type", "application/json")
            .removeHeader("Pragma")
        val request = requestBuilder.build()
        it.proceed(request)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(providesAuthInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(httpLoggerInterceptor)
        }
        return client.build()
    }

    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}