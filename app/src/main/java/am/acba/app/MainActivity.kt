package am.acba.app

import am.acba.acbacommons.base.BaseActivityWithViewModel
import am.acba.app.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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
    }
}