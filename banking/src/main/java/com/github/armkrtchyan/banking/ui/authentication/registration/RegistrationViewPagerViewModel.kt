package com.github.armkrtchyan.banking.ui.authentication.registration

import com.github.armkrtchyan.banking.ui.authentication.registration.models.RegistrationRequestModel
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.shared.extensions.log

class RegistrationViewPagerViewModel : BaseViewModel() {
    fun register(mRequestModel: RegistrationRequestModel) {
        "Register request".log()
    }
}