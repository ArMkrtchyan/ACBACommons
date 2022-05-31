package com.github.armkrtchyan.common.widgets

import com.github.armkrtchyan.common.R
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

class ToolbarACBA : Toolbar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.ToolbarACBA).apply {
            recycle()
        }
    }
}