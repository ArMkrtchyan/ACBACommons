package am.acba.acbacommons.base

import am.acba.acbacommons.databinding.LayoutErrorBinding
import am.acba.acbacommons.databinding.LayoutLoadingBinding
import am.acba.acbacommons.state.State
import am.acba.acbacommons.validators.Validator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open val keepBindingAlive: Boolean = true
    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding
    protected abstract val inflate: (LayoutInflater) -> VB
    private val rootGroup by lazy { FrameLayout(this) }
    var mFragmentViewModel: BaseViewModel? = null
    private val layoutLoading by lazy { LayoutLoadingBinding.inflate(layoutInflater) }
    private val layoutError by lazy { LayoutErrorBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::_binding.isInitialized || !keepBindingAlive) {
            _binding = inflate(layoutInflater)
        }
        rootGroup.removeAllViews()
        rootGroup.addView(_binding.root)
        setContentView(rootGroup)
        lifecycleScope.launchWhenResumed {
            mFragmentViewModel?.stateFlow?.collectLatest(::setState)
        }
    }

    protected fun validate(root: View = _binding.root) {
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

    protected fun setState(state: State) {
        rootGroup.removeView(layoutError.root)
        rootGroup.removeView(layoutLoading.root)
        layoutError.errorMessage.text = ""
        layoutError.retry.setOnClickListener(null)
        when (state) {
            is State.Loading -> {
                rootGroup.addView(layoutLoading.root)
            }
            is State.Empty -> {
            }
            is State.Error -> {
                rootGroup.addView(layoutError.root)
            }
            is State.Success -> {
            }
        }
    }
}