package am.acba.acbacommons.base

import am.acba.acbacommons.databinding.LayoutErrorBinding
import am.acba.acbacommons.databinding.LayoutLoadingBinding
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest

abstract class BaseActivityWithViewModel<VB : ViewBinding, VIEWMODEL : BaseViewModel> : BaseActivity<VB>() {
    protected abstract val mViewModel: VIEWMODEL
    private val layoutLoading by lazy { LayoutLoadingBinding.inflate(layoutInflater) }
    private val layoutError by lazy { LayoutErrorBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            mViewModel.stateFlow.collectLatest {
                val rootGroup = (mBinding.root as ViewGroup)
                when (it) {
                    1 -> {
                        rootGroup.removeView(layoutError.root)
                     //   if (!rootGroup.children.contains(layoutLoading.root))
                            rootGroup.addView(layoutLoading.root)
                    }
                    2 -> {
                        rootGroup.removeView(layoutLoading.root)
                        if (!rootGroup.children.contains(layoutError.root))
                            rootGroup.addView(layoutError.root)
                    }
                    else -> {
                        rootGroup.removeView(layoutError.root)
                        rootGroup.removeView(layoutLoading.root)
                    }
                }
            }
        }
    }
}