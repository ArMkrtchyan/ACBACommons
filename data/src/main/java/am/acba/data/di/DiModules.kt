package am.acba.data.di

import am.acba.acbacommons.retrofit.BaseInterceptorsHelper
import am.acba.data.InterceptorHelper
import am.acba.data.repositories.RatesRepositoryImpl
import am.acba.data.services.ServiceFactory
import am.acba.domain.repositories.RatesRepository
import org.koin.dsl.module

val dataModule = module {
    single<am.acba.data.IDataSource> { am.acba.data.DataSource(mNetworkConnection = get()) }
}
val repositoriesModule = module {
    factory { ServiceFactory.createRatesService(retrofit = get()) }
    single<RatesRepository> { RatesRepositoryImpl(mDataSource = get(), mService = get()) }
}
val interceptorModule = module {
    single<BaseInterceptorsHelper> { InterceptorHelper() }
}