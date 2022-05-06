package am.acba.acbacommons.base

import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithViewModel<VB : ViewBinding, VIEWMODEL : BaseViewModel> : BaseFragment<VB>() {
    protected abstract val mViewModel: VIEWMODEL
    override fun onResume() {
        super.onResume()
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).mFragmentViewModel = mViewModel
        }
    }
}