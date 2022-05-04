package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import am.acba.acbacommons.validators.Validator
import am.acba.acbacommons.validators.ValidatorEnum
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import kotlin.properties.Delegates

class EditTextACBA : AppCompatEditText, Validator {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private var errorMessage: String? = null
    private var mIsRequiredForValidation by Delegates.notNull<Boolean>()
    private lateinit var mValidatorEnum: ValidatorEnum
    private var textInputLayoutACBA: TextInputLayoutACBA? = null
    private val mTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textInputLayoutACBA?.error = null
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
            mIsRequiredForValidation = mValidatorEnum != null && mValidatorEnum != ValidatorEnum.NONE
            errorMessage = getString(R.styleable.EditTextACBA_errorMessage)
            recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (parent.parent is TextInputLayoutACBA) textInputLayoutACBA = parent.parent as TextInputLayoutACBA
    }


    override fun isRequiredForValidation() = mIsRequiredForValidation

    override fun isValid(): Boolean {
        if (!mValidatorEnum.isMatch(text)) showError()
        return mValidatorEnum.isMatch(text)
    }

    override fun showError() {
        textInputLayoutACBA?.let { inputLayout ->
            errorMessage?.let { error -> inputLayout.error = error } ?: run { inputLayout.error = mValidatorEnum.errorMessage }
            removeTextChangedListener(mTextWatcher)
            addTextChangedListener(mTextWatcher)
        }
    }
}
