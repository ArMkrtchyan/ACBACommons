package com.github.armkrtchyan.app

import com.github.armkrtchyan.common.base.BaseApplication
import com.github.armkrtchyan.app.di.appModule
import com.github.armkrtchyan.app.di.workerModule
import com.github.armkrtchyan.data.di.dataModule
import com.github.armkrtchyan.data.di.interceptorModule
import com.github.armkrtchyan.data.di.repositoriesModule
import org.koin.core.module.Module

class ACBAApplication : BaseApplication() {
    override val modules: List<Module>
        get() = listOf(appModule, workerModule, repositoriesModule, dataModule, interceptorModule)

}