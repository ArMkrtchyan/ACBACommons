package com.github.armkrtchyan.banking.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.shared.extensions.log
import com.github.armkrtchyan.domain.models.RatesDomainModel
import com.github.armkrtchyan.domain.repositories.ConfigsRepository
import com.github.armkrtchyan.domain.repositories.RatesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stateHandler: SavedStateHandle,
    private val mRatesRepository: RatesRepository,
    private val mConfigsRepository: ConfigsRepository
) : BaseViewModel() {
    private val _buttonVisibilityFlow = MutableStateFlow(false)
    val buttonVisibilityFlow: StateFlow<Boolean>
        get() = _buttonVisibilityFlow

    fun getRates() {
        stateHandler.get<RatesDomainModel>("Rates").log()
        viewModelScope.launch {
            handleNetworkResult(mRatesRepository.getRates()) {
                stateHandler["Rates"] = it
            }
        }
    }

    fun getRates2() {
        viewModelScope.launch {
            handlePairNetworkResult(mRatesRepository.getRates(), mRatesRepository.getRates()) {

            }
        }
    }

    fun getRates3() {
        viewModelScope.launch {
            handleTripleNetworkResult(mRatesRepository.getRates(), mRatesRepository.getRates(), mRatesRepository.getRates()) {

            }
        }
    }

    fun initConfig() {
        viewModelScope.launch {
            handlePairNetworkResult(mConfigsRepository.getConfig(), mConfigsRepository.getDefaultConfig()) {
                _buttonVisibilityFlow.value = it.first.ui?.homeScreen?.isButtonVisible ?: it.second.ui.homeScreen.isButtonVisible
            }
        }
    }

    override fun retry() {
        getRates()
    }
}