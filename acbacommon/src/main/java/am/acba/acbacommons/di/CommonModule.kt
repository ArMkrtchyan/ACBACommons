package am.acba.acbacommons.di

import am.acba.acbacommons.shared.NetworkConnection
import am.acba.acbacommons.shared.PreferencesManager
import am.acba.acbacommons.retrofit.RetrofitModule
import org.koin.dsl.module

val commonDataModule = module {
    single { PreferencesManager(context = get()) }
    single { NetworkConnection(context = get()) }
    single { RetrofitModule(mPreferencesManager = get(), context = get(), interceptors = get()).providesRetrofit() }
}