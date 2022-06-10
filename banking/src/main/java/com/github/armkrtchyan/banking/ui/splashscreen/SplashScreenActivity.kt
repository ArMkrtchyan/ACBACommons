package com.github.armkrtchyan.banking.ui.splashscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.github.armkrtchyan.banking.databinding.ActivitySplashScreenBinding
import com.github.armkrtchyan.common.base.BaseActivityWithViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivityWithViewModel<ActivitySplashScreenBinding, SplashScreenViewModel>() {
    override val inflate: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate
    override val mViewModel: SplashScreenViewModel
        get() = getViewModel()

}