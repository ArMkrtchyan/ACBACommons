package core.common.base

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest

abstract class BaseViewModelFragment<VIEWMODEL : BaseViewModel> : BaseFragment() {
    protected abstract val mViewModel: VIEWMODEL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is BaseActivity<*>) {
            lifecycleScope.launchWhenResumed {
                mViewModel.stateFlow.collectLatest((activity as BaseActivity<*>)::setState)
            }
        }
    }
}