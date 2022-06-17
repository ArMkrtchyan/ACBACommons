package com.github.armkrtchyan.banking.ui.authentication.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.armkrtchyan.banking.ui.authentication.registration.models.RegistrationRequestModel
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.shared.extensions.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationViewPagerViewModel : BaseViewModel() {
    fun register(mRequestModel: RegistrationRequestModel) {
        "Register request".log()
    }

    val firstLiveData = MutableLiveData<Int>()
    val secondLiveData = MutableLiveData<String>()
    val thirdLiveData = MutableLiveData<Boolean>()
    val fourthLiveData = MutableLiveData<Long>()

    fun firstRequest() {
        viewModelScope.launch {
            delay(2000)
            firstLiveData.postValue(1)
        }
    }

    fun secondRequest() {
        viewModelScope.launch {
            delay(3000)
            secondLiveData.postValue("1")
        }
    }

    fun thirdRequest() {
        viewModelScope.launch {
            delay(1000)
            thirdLiveData.postValue(true)
        }
    }

    fun fourthRequest() {
        viewModelScope.launch {
            delay(4000)
            fourthLiveData.postValue(1)
        }
    }
}