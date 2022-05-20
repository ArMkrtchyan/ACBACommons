package am.acba.app

import am.acba.app.di.appModule
import am.acba.app.di.workerModule
import am.acba.data.di.dataModule
import android.app.Application
import androidx.work.Configuration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ACBAApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()
        startKoin {
           // androidLogger(level = Level.DEBUG)
            androidContext(this@ACBAApplication)
            workManagerFactory()
            modules(listOf(appModule, dataModule, workerModule))
        }
    }
}