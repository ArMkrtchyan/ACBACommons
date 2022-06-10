package com.github.armkrtchyan.banking.ui.splashscreen

import androidx.lifecycle.viewModelScope
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.domain.repositories.ConfigsRepository
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val mConfigsRepository: ConfigsRepository
) : BaseViewModel() {

    fun initConfig() {
        viewModelScope.launch {
            handlePairNetworkResult(mConfigsRepository.getConfig(), mConfigsRepository.getDefaultConfig()) {

            }
        }
    }

    override fun retry() {
    }
}