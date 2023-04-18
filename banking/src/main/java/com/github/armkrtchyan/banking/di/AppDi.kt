package com.github.armkrtchyan.banking.di

import com.github.armkrtchyan.banking.GetRatesWorker
import com.github.armkrtchyan.banking.ui.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashScreenViewModel(mConfigsRepository = get()) }
}
val workerModule = module {
    worker {
        GetRatesWorker(get(), get(), get())
    }
}