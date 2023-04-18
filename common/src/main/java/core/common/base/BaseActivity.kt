package core.common.base

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import core.common.exceptions.AuthException
import core.common.shared.alertDialog.LoadingAlertDialog
import core.common.shared.extensions.getByResourceId
import core.common.state.State
import core.common.validators.Validator
import java.util.*

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open val keepBindingAlive: Boolean = true
    private lateinit var _binding: VB
    val mBinding: VB
        get() = _binding
    protected abstract val inflate: (LayoutInflater) -> VB
    protected abstract fun VB.initView()
    private var mLoadingDialog: LoadingAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::_binding.isInitialized || !keepBindingAlive) {
            _binding = inflate(layoutInflater)
            _binding.initView()
        }
        setContentView(_binding.root)
    }

    protected fun validate(root: View = _binding.root) {
        if (root is ViewGroup) for (view in root.children) {
            validate(view)
        } else if (root is Validator) root.isValid()
    }

    fun setState(state: State) {
        hideSoftInput()
        when (state) {
            is State.Loading -> showLoading()
            is State.Empty -> hideLoading()
            is State.Error -> {
                if (state.throwable is AuthException) {
                    logout()
                }
                showError(state.throwable)
                hideLoading()
            }
            is State.Success -> hideLoading()
        }
    }

    open fun logout() {}

//    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
//        if (event?.action == MotionEvent.ACTION_DOWN) {
//            val focusedView = currentFocus
//            if (focusedView is EditText) {
//                val out = Rect()
//                focusedView.getGlobalVisibleRect(out)
//                if (!out.contains(event.rawX.toInt(), event.rawY.toInt())) {
//                    focusedView.clearFocus()
//                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
//                }
//            }
//        }
//        return super.dispatchTouchEvent(event)
//    }

    override fun attachBaseContext(newBase: Context?) {
        val locale = Locale("hy")
        val newConfig = Configuration(newBase?.resources?.configuration)
        Locale.setDefault(locale)
        newConfig.setLocale(locale)
        super.attachBaseContext(newBase?.createConfigurationContext(newConfig))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun hideSoftInput() {
        val view: View? = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setTextColor(getByResourceId<ColorStateList>(android.R.color.white))
            .setBackgroundTint(getByResourceId(android.R.color.black))
            .show()
    }

    open fun showError(error: Throwable) {

    }

    open fun showLoading() {
        mLoadingDialog?.dismiss()
        mLoadingDialog = LoadingAlertDialog.Builder(this).build()
    }

    open fun hideLoading() {
        mLoadingDialog?.dismiss()
    }
}