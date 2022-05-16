package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class TextInputLayoutACBA : TextInputLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.TextInputLayoutACBA).apply {
            recycle()
        }
    }
}