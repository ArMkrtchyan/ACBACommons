package com.github.armkrtchyan.banking

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.github.armkrtchyan.banking.databinding.ActivityMainBinding
import com.github.armkrtchyan.common.base.BaseActivityWithViewModel
import com.github.armkrtchyan.common.shared.extensions.log
import com.github.armkrtchyan.domain.models.RatesDomainModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : BaseActivityWithViewModel<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel
        get() = stateViewModel<MainViewModel>().value

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}