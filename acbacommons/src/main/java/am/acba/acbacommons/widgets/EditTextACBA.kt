package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import am.acba.acbacommons.validators.Validator
import am.acba.acbacommons.validators.ValidatorEnum
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import kotlin.properties.Delegates

class EditTextACBA @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defArrStyle: Int = 0
) : AppCompatEditText(context, attrs, defArrStyle), Validator {
    private var mIsRequiredForValidation by Delegates.notNull<Boolean>()
    private var mValidatorEnum: ValidatorEnum

    init {
        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.EditTextACBA)
        mValidatorEnum =
            ValidatorEnum.values()[incomingValues.getInt(R.styleable.EditTextACBA_validator, 0)]
        mIsRequiredForValidation = mValidatorEnum != ValidatorEnum.NONE
        incomingValues.recycle()
    }

    override fun isRequiredForValidation() = mIsRequiredForValidation

    override fun isValid(): Boolean {
        return mValidatorEnum.isMatch(text)
    }

}