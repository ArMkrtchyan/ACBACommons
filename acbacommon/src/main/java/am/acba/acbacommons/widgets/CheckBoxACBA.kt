package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import am.acba.acbacommons.shared.Extensions.showToast
import am.acba.acbacommons.validators.Validator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import kotlin.properties.Delegates

class CheckBoxACBA : AppCompatCheckBox, Validator {

    private var mIsRequiredForValidation by Delegates.notNull<Boolean>()
    private var mErrorMessage: String? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.CheckBoxACBA).apply {
            mErrorMessage = getString(R.styleable.CheckBoxACBA_errorMessage)
            mIsRequiredForValidation = getBoolean(R.styleable.CheckBoxACBA_mustBeChecked, false)
            recycle()
        }
    }

    override fun isRequiredForValidation() = mIsRequiredForValidation

    override fun isValid(): Boolean {
        if (!isChecked) return showError(mErrorMessage)
        return isChecked
    }

    override fun showError(message: String?): Boolean {
        context.showToast(message)
        return false
    }

    override fun setDefaultState() {

    }
}