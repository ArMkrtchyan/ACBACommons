package am.acba.app.di

import am.acba.app.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(stateHandler = get(), mRatesRepository = get()) }
}