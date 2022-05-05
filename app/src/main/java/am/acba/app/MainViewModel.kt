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
                Log.i("MakeRequestTag", "Success: $it")
            }
        }
    }
    fun getRates2() {
        viewModelScope.launch {
            handleNetworkResult(mRatesRepository.getRates()) {
                Log.i("MakeRequestTag", "Success: $it")
            }
        }
    }
    fun getRates3() {
        viewModelScope.launch {
            handleNetworkResult(mRatesRepository.getRates()) {
                Log.i("MakeRequestTag", "Success: $it")
            }
        }
    }
}