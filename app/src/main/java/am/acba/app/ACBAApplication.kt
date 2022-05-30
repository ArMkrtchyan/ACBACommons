package am.acba.app

import am.acba.acbacommons.base.BaseApplication
import am.acba.app.di.appModule
import am.acba.app.di.workerModule
import am.acba.data.di.dataModule
import am.acba.data.di.interceptorModule
import am.acba.data.di.repositoriesModule
import org.koin.core.module.Module

class ACBAApplication : BaseApplication() {
    override val modules: List<Module>
        get() = listOf(appModule, workerModule, repositoriesModule, dataModule, interceptorModule)

}