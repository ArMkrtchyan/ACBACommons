package com.github.armkrtchyan.banking

import com.github.armkrtchyan.banking.di.appModule
import com.github.armkrtchyan.banking.di.workerModule
import com.github.armkrtchyan.data.di.dataModule
import com.github.armkrtchyan.data.di.interceptorModule
import com.github.armkrtchyan.data.di.repositoriesModule
import core.common.base.BaseApplication
import org.koin.core.module.Module

class BankingApplication : BaseApplication() {
    override val modules: List<Module>
        get() = listOf(appModule, workerModule, repositoriesModule, dataModule, interceptorModule)

}