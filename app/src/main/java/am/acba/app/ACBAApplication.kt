package am.acba.app

import am.acba.app.di.appModule
import am.acba.data.di.dataModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ACBAApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ACBAApplication)
          //  androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, dataModule))
        }
    }
}