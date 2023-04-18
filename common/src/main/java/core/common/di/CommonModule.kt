package core.common.di

import core.common.retrofit.RetrofitModule
import core.common.shared.NetworkConnection
import core.common.shared.PreferencesManager
import org.koin.dsl.module

val commonDataModule = module {
    single { PreferencesManager() }
    single { NetworkConnection(context = get()) }
    single { RetrofitModule(interceptors = get()).providesRetrofit() }
}