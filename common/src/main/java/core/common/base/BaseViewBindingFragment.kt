package core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.viewbinding.ViewBinding
import core.common.shared.Inflater
import core.common.validators.Validator

abstract class BaseViewBindingFragment<VB : ViewBinding> : BaseFragment() {

    protected open val keepBindingAlive: Boolean = true
    private lateinit var _binding: VB
    val mBinding: VB
        get() = _binding
    protected abstract val inflate: Inflater<VB>
    protected abstract fun VB.initView()
    open val toolbar: Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::_binding.isInitialized || !keepBindingAlive) {
            _binding = inflate(inflater, container, false)
            _binding.initView()
        }
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        toolbar?.let {
            mActivity.setSupportActionBar(it)
        }
    }

    protected fun validate(root: View = mBinding.root) {
        if (root is ViewGroup) {
            for (view in root.children) {
                validate(view)
            }
        } else if (root is Validator) {
            if (root.isRequiredForValidation()) {
                root.isValid()
            }
        }
    }
}