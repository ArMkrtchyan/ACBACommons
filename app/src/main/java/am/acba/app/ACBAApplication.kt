package am.acba.app

import am.acba.app.di.appModule
import am.acba.data.di.dataModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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