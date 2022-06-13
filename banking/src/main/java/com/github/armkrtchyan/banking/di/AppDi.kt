package com.github.armkrtchyan.banking.di

import com.github.armkrtchyan.banking.GetRatesWorker
import com.github.armkrtchyan.banking.ui.authentication.registration.RegistrationViewPagerViewModel
import com.github.armkrtchyan.banking.ui.authentication.registration.emailverification.EmailViewModel
import com.github.armkrtchyan.banking.ui.authentication.registration.phoneverification.PhoneNumberViewModel
import com.github.armkrtchyan.banking.ui.home.HomeViewModel
import com.github.armkrtchyan.banking.ui.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashScreenViewModel(mConfigsRepository = get()) }
    viewModel { HomeViewModel(stateHandler = get(), mRatesRepository = get(), mConfigsRepository = get()) }
    viewModel { RegistrationViewPagerViewModel() }
    viewModel { PhoneNumberViewModel() }
    viewModel { EmailViewModel() }
}
val workerModule = module {
    worker {
        GetRatesWorker(get(), get(), get())
    }
}