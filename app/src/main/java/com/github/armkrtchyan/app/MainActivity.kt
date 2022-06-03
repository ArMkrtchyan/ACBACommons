package com.github.armkrtchyan.app

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.github.armkrtchyan.app.databinding.ActivityMainBinding
import com.github.armkrtchyan.common.base.BaseActivityWithViewModel
import com.github.armkrtchyan.common.shared.extensions.getByResourceId
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
        mBinding.validate.apply {
            text = getByResourceId(R.string.app_name)
            backgroundTintList = getByResourceId(R.color.colorAccentLight)
            setTextColor(getByResourceId<ColorStateList>(R.color.colorPrimaryLight))
        }
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val getRatesRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<GetRatesWorker>().setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(getRatesRequest)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(getRatesRequest.id).observe(this) {
            it.state.name.log("WorkManager")
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val res = Gson().fromJson(it.outputData.getString("result"), RatesDomainModel::class.java)
                    res.log("WorkManager")
                }
            }
        }
    }
}