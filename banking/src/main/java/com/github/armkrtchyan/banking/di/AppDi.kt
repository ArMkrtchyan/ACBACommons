package com.github.armkrtchyan.banking.di

import com.github.armkrtchyan.banking.GetRatesWorker
import com.github.armkrtchyan.banking.MainViewModel
import com.github.armkrtchyan.banking.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(stateHandler = get(), mRatesRepository = get(), mConfigsRepository = get()) }
    viewModel { HomeViewModel(stateHandler = get(), mRatesRepository = get(), mConfigsRepository = get()) }
}
val workerModule = module {
    worker {
        GetRatesWorker(get(), get(), get())
    }
}