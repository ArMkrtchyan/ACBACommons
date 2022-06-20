package com.github.armkrtchyan.banking.ui.authentication

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.github.armkrtchyan.banking.databinding.ActivityAuthenticationBinding
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseViewPagerFragment
import com.github.armkrtchyan.common.base.BaseActivity

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override val inflate: (LayoutInflater) -> ActivityAuthenticationBinding
        get() = ActivityAuthenticationBinding::inflate

    override fun onBackPressed() {
        if ((supportFragmentManager.primaryNavigationFragment as NavHostFragment).childFragmentManager.fragments[0] is BaseViewPagerFragment<*, *>) {
            if (!((supportFragmentManager.primaryNavigationFragment as NavHostFragment).childFragmentManager.fragments[0] as BaseViewPagerFragment<*, *>).navigatePreviousPage())
                super.onBackPressed()
        } else
            super.onBackPressed()
    }
}