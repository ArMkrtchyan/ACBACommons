package am.acba.app.di

import am.acba.app.GetRatesWorker
import am.acba.app.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(stateHandler = get(), mRatesRepository = get()) }
}
val workerModule = module {
    worker {
        GetRatesWorker(get(), get(), get())
    }
}