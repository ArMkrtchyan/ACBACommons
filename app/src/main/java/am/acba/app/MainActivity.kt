package am.acba.app

import am.acba.acbacommons.base.BaseActivityWithViewModel
import am.acba.app.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivityWithViewModel<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel
        get() = viewModel<MainViewModel>().value

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            mViewModel.getRates()
            delay(500)
            mViewModel.getRates2()
            delay(500)
            mViewModel.getRates3()
        }
        mBinding.apply {
            validate.setOnClickListener {
                validate()
                Log.i("ClickTag", "Clicked")
            }
        }
    }
}