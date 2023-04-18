package core.common.base

import android.app.Application
import androidx.work.Configuration
import core.common.BuildConfig
import core.common.di.commonDataModule
import core.common.exceptions.CrashHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application(), Configuration.Provider {

    abstract val modules: List<Module>

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder().setMinimumLoggingLevel(android.util.Log.INFO).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(commonDataModule)).modules(modules)
        }
        setupChuckerErrors()
    }

    private fun setupChuckerErrors() {
        if (BuildConfig.DEBUG) {
            val systemHandler = Thread.getDefaultUncaughtExceptionHandler()
            Thread.setDefaultUncaughtExceptionHandler(CrashHandler(systemHandler, this))
        }
    }
}