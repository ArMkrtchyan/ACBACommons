package am.acba.acbacommons.base

import am.acba.acbacommons.validators.Validator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected open val keepBindingAlive: Boolean = true
    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding
    protected abstract val inflate: (LayoutInflater) -> VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::_binding.isInitialized || !keepBindingAlive) {
            _binding = inflate(layoutInflater)
        }
        setContentView(_binding.root)
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