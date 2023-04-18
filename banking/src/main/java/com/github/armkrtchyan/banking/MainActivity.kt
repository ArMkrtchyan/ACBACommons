package com.github.armkrtchyan.banking

import android.view.LayoutInflater
import com.github.armkrtchyan.banking.databinding.ActivityMainBinding
import core.common.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun ActivityMainBinding.initView() {

    }
}