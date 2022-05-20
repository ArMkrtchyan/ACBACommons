package am.acba.app

import am.acba.acbacommons.base.BaseActivityWithViewModel
import am.acba.app.databinding.ActivityMainBinding
import am.acba.domain.models.RatesDomainModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.work.*
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : BaseActivityWithViewModel<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel
        get() = stateViewModel<MainViewModel>().value

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            validate.setOnClickListener {
                validate()
                Log.i("ClickTag", "Clicked")
                mViewModel.getRates()
                mViewModel.getRates2()
                mViewModel.getRates3()
            }
        }
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val getRatesRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<GetRatesWorker>().setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(getRatesRequest)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(getRatesRequest.id).observe(this) {
            when (it.state) {
                WorkInfo.State.RUNNING -> {
                    Log.d("WorkManager", "RUNNING")
                }
                WorkInfo.State.ENQUEUED -> {
                    Log.d("WorkManager", "ENQUEUED")
                }
                WorkInfo.State.SUCCEEDED -> {
                    Log.d("WorkManager", "SUCCEEDED")
                    val res = Gson().fromJson(it.outputData.getString("result"), RatesDomainModel::class.java)
                    Log.d("WorkManager", "$res")
                }
                WorkInfo.State.FAILED -> {
                    Log.d("WorkManager", "FAILED")
                }
                WorkInfo.State.BLOCKED -> {
                    Log.d("WorkManager", "BLOCKED")
                }
                WorkInfo.State.CANCELLED -> {
                    Log.d("WorkManager", "CANCELLED")
                }
            }
        }
    }
}