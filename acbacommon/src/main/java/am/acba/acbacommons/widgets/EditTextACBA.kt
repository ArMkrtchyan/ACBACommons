package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import am.acba.acbacommons.validators.Validator
import am.acba.acbacommons.validators.ValidatorEnum
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates

class EditTextACBA : TextInputEditText, Validator {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private var mErrorMessage: String? = null
    private var mEmptyErrorMessage: String? = null
    private var mMinLengthErrorMessage: String? = null
    private var mRegex: String? = null
    private var mMinLength = 0
    private var mIsRequiredForValidation by Delegates.notNull<Boolean>()
    private lateinit var mValidatorEnum: ValidatorEnum
    private var mTextInputLayoutACBA: TextInputLayoutACBA? = null
    private val mTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mTextInputLayoutACBA?.error = null
                mTextInputLayoutACBA?.isErrorEnabled = false
                removeListener()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
    }

    private fun removeListener() {
        removeTextChangedListener(mTextWatcher)
    }

    private fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.EditTextACBA).apply {
            mValidatorEnum = ValidatorEnum.values()[getInt(R.styleable.EditTextACBA_validator, 0)]
            mIsRequiredForValidation = mValidatorEnum != ValidatorEnum.NONE
            mErrorMessage = getString(R.styleable.EditTextACBA_errorMessage)
            mRegex = getString(R.styleable.EditTextACBA_regex)
            mMinLength = getInt(R.styleable.EditTextACBA_minLength,0)
            recycle()
        }
        try {
            mEmptyErrorMessage = context.getString(context.resources.getIdentifier("empty_message", "string", context.packageName))
            mMinLengthErrorMessage = String.format(context.getString(context.resources.getIdentifier("min_length_message", "string", context.packageName)),mMinLength)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (parent.parent is TextInputLayoutACBA) mTextInputLayoutACBA = parent.parent as TextInputLayoutACBA
    }

    override fun isRequiredForValidation() = mIsRequiredForValidation

    override fun isValid(): Boolean {
        if (text?.isEmpty() == true) return showError(mEmptyErrorMessage)
        if ((text?.length ?: 0) < mMinLength) return showError(mMinLengthErrorMessage)
        if (!mValidatorEnum.isMatch(text, regex = mRegex)) return showError(mErrorMessage)
        return true
    }

    override fun showError(message: String?): Boolean {
        mTextInputLayoutACBA?.let { inputLayout ->
            inputLayout.error = message
            removeTextChangedListener(mTextWatcher)
            addTextChangedListener(mTextWatcher)
            mTextInputLayoutACBA?.isErrorEnabled = true
        }
        return false
    }
}
