package com.github.armkrtchyan.banking.ui.authentication.registration.base

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.github.armkrtchyan.common.base.BaseViewBindingViewModelFragment
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.widgets.CommonButton

abstract class BaseRegistrationFragment<VB : ViewBinding, VIEWMODEL : BaseViewModel> : BaseViewBindingViewModelFragment<VB, VIEWMODEL>() {
    abstract val mNextButton: CommonButton
    open val mTitle: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireParentFragment() is NavHostFragment) {
            if ((requireParentFragment() as NavHostFragment).childFragmentManager.fragments[0] is BaseRegistrationViewPagerFragment<*, *>)
                mNextButton.setOnClickListener((requireParentFragment() as NavHostFragment).childFragmentManager.fragments[0] as BaseRegistrationViewPagerFragment<*, *>)
        }
    }
}