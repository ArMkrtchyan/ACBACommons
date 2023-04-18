package core.common.retrofit

import android.content.Context
import core.common.R
import core.common.constants.BundleKeys
import core.common.shared.PreferencesManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule(
    private val interceptors: BaseInterceptorsHelper,
) : KoinComponent {
    private val mContext: Context = get()
    private val mPreferences: PreferencesManager = get()

    init {
        if (mPreferences.findByKey<String>(BundleKeys.BASE_URL).isEmpty())
            mPreferences.saveByKey(BundleKeys.BASE_URL, mContext.getString(R.string.base_url))
    }

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
            .baseUrl(BundleKeys.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}