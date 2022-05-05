package am.acba.data.di

import am.acba.acbacommons.shared.NetworkConnection
import am.acba.acbacommons.shared.PreferencesManager
import am.acba.data.dataSource.DataSource
import am.acba.data.dataSource.IDataSource
import am.acba.data.dataSource.RetrofitModule
import am.acba.data.repositories.RatesRepositoryImpl
import am.acba.data.services.ServiceFactory
import am.acba.domain.repositories.RatesRepository
import org.koin.dsl.module

val dataModule = module {
    single { PreferencesManager(context = get()) }
    single { NetworkConnection(context = get()) }
    single { RetrofitModule(sharedPreferences = get(), context = get()).providesRetrofit() }
    single<IDataSource> { DataSource(mPreferencesManager = get(), mNetworkConnection = get()) }
    factory { ServiceFactory.createRatesService(retrofit = get()) }
    single<RatesRepository> { RatesRepositoryImpl(mDataSource = get(), mService = get()) }
}