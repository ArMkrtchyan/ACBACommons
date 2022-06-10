package com.github.armkrtchyan.banking.ui.authentication.registration.base

import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.base.BaseViewModelFragment

abstract class BaseRegistrationViewPagerFragment<VIEWMODEL : BaseViewModel> : BaseViewModelFragment<VIEWMODEL>() {
    abstract val mTitle: String
    abstract val mViewPagerFragments: List<BaseRegistrationFragment<*, *>>
}