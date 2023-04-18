package com.github.armkrtchyan.banking.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.work.*
import com.github.armkrtchyan.banking.GetRatesWorker
import com.github.armkrtchyan.banking.databinding.FragmentSplashScreenBinding
import com.github.armkrtchyan.domain.models.RatesDomainModel
import com.google.gson.Gson
import core.common.base.BaseViewBindingFragment
import core.common.shared.Inflater
import core.common.shared.extensions.log

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseViewBindingFragment<FragmentSplashScreenBinding>() {
    override val inflate: Inflater<FragmentSplashScreenBinding>
        get() = FragmentSplashScreenBinding::inflate

    override fun FragmentSplashScreenBinding.initView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val getRatesRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<GetRatesWorker>().setConstraints(constraint).build()
        WorkManager.getInstance(requireActivity()).enqueue(getRatesRequest)
        WorkManager.getInstance(requireActivity()).getWorkInfoByIdLiveData(getRatesRequest.id).observe(requireActivity()) {
            it.state.name.log("WorkManager")
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    val res = Gson().fromJson(it.outputData.getString("result"), RatesDomainModel::class.java)
                    res.log("WorkManager")
                }
                WorkInfo.State.FAILED -> "FAILED".log("WorkManager")
                WorkInfo.State.CANCELLED -> "CANCELLED".log("WorkManager")
                else -> {}
            }
        }
    }
}