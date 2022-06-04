package com.github.armkrtchyan.app.di

import com.github.armkrtchyan.app.GetRatesWorker
import com.github.armkrtchyan.app.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(stateHandler = get(), mRatesRepository = get(), mConfigsRepository = get()) }
}
val workerModule = module {
    worker {
        GetRatesWorker(get(), get(), get())
    }
}