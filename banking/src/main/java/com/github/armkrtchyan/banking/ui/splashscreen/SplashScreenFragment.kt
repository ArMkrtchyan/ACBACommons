package com.github.armkrtchyan.banking.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.github.armkrtchyan.banking.GetRatesWorker
import com.github.armkrtchyan.banking.databinding.FragmentSplashScreenBinding
import com.github.armkrtchyan.banking.ui.authentication.AuthenticationActivity
import com.github.armkrtchyan.common.base.BaseViewBindingFragment
import com.github.armkrtchyan.common.shared.Inflater
import com.github.armkrtchyan.common.shared.extensions.launch
import com.github.armkrtchyan.common.shared.extensions.log
import com.github.armkrtchyan.domain.models.RatesDomainModel
import com.google.gson.Gson
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseViewBindingFragment<FragmentSplashScreenBinding>() {
    override val inflate: Inflater<FragmentSplashScreenBinding>
        get() = FragmentSplashScreenBinding::inflate

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
                    navigateToAuthActivityDelayed()
                }
                WorkInfo.State.FAILED -> navigateToAuthActivityDelayed()
                WorkInfo.State.CANCELLED -> navigateToAuthActivityDelayed()
            }
        }
    }

    private fun navigateToAuthActivityDelayed() {
        lifecycleScope.launchWhenResumed {
            delay(2000)
            launch<AuthenticationActivity>(finish = true)
        }
    }
}