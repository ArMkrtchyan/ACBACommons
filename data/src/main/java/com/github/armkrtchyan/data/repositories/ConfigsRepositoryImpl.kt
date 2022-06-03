package com.github.armkrtchyan.data.repositories

import android.content.Context
import com.github.armkrtchyan.domain.models.Config
import com.github.armkrtchyan.domain.models.DefaultConfig
import com.github.armkrtchyan.domain.repositories.ConfigsRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.io.InputStream
import java.nio.charset.Charset

class ConfigsRepositoryImpl : ConfigsRepository, KoinComponent {
    private val context: Context = get()
    private val config by lazy { Gson().fromJson(getFromAssets("configs.json"), Config::class.java) }
    private val defaultConfig by lazy { Gson().fromJson(getFromAssets("default_configs.json"), DefaultConfig::class.java) }
    override suspend fun getDefaultConfig(): Flow<DefaultConfig> {
        return flow {
            emit(defaultConfig)
        }
    }

    override suspend fun getConfig(): Flow<Config> {
        return flow {
            emit(config)
        }
    }

    private fun getFromAssets(fileName: String): String {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}