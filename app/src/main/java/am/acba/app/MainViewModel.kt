package am.acba.app

import am.acba.acbacommons.base.BaseViewModel
import am.acba.domain.models.RatesDomainModel
import am.acba.domain.repositories.RatesRepository
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val stateHandler: SavedStateHandle,
    private val mRatesRepository: RatesRepository
) : BaseViewModel() {

    fun getRates() {
        Log.i("SavedTag", stateHandler.get<RatesDomainModel>("Rates").toString())
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

    override fun retry() {
        getRates()
    }
}