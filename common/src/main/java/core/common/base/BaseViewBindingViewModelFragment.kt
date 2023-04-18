package core.common.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest

abstract class BaseViewBindingViewModelFragment<VB : ViewBinding, VIEWMODEL : BaseViewModel> : BaseViewBindingFragment<VB>() {
    protected abstract val mViewModel: VIEWMODEL
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is BaseActivity<*>) {
            lifecycleScope.launchWhenResumed {
                mViewModel.stateFlow.collectLatest((activity as BaseActivity<*>)::setState)
            }
        }
    }
}