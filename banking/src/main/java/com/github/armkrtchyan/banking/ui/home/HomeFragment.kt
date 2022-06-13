package com.github.armkrtchyan.banking.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.github.armkrtchyan.banking.databinding.FragmentHomeBinding
import com.github.armkrtchyan.common.base.BaseViewBindingViewModelFragment
import com.github.armkrtchyan.common.shared.Inflater
import com.github.armkrtchyan.common.shared.extensions.log
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.getStateViewModel

class HomeFragment : BaseViewBindingViewModelFragment<FragmentHomeBinding, HomeViewModel>() {
    override val inflate: Inflater<FragmentHomeBinding>
        get() = FragmentHomeBinding::inflate
    override val mViewModel: HomeViewModel
        get() = getStateViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.initConfig()
        mBinding.apply {
            validate.setOnClickListener {
                validate()
                "Clicked".log("Click")
                mViewModel.getRates()
                mViewModel.getRates2()
                mViewModel.getRates3()
            }
            lifecycleScope.launchWhenStarted {
                mViewModel.buttonVisibilityFlow.collectLatest {
                    validate.isVisible = it
                }
            }
        }
    }
}