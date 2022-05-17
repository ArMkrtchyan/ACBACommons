package am.acba.app

import am.acba.acbacommons.base.BaseViewModel
import am.acba.domain.repositories.RatesRepository
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val mRatesRepository: RatesRepository) : BaseViewModel() {

    fun getRates() {
        viewModelScope.launch {
            handleNetworkResult(mRatesRepository.getRates()) {

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

    override fun retry() {
        getRates()
    }
}