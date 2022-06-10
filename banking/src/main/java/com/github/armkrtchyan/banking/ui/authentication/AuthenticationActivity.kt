package com.github.armkrtchyan.banking.ui.authentication

import android.view.LayoutInflater
import com.github.armkrtchyan.banking.databinding.ActivityAuthenticationBinding
import com.github.armkrtchyan.common.base.BaseActivity

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override val inflate: (LayoutInflater) -> ActivityAuthenticationBinding
        get() = ActivityAuthenticationBinding::inflate
}