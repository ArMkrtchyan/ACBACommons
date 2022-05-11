package am.acba.acbacommons.widgets

import am.acba.acbacommons.R
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class ButtonACBA : MaterialButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private var mDoubleClick = false
    private var mIsClicked = false
    private var mClickInterval = 800

    private fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.ButtonACBA).apply {
            mDoubleClick = getBoolean(R.styleable.ButtonACBA_isPreventClick, true)
            mClickInterval = getInt(R.styleable.ButtonACBA_clickInterval, 800)
            recycle()
        }
    }

    override fun setOnClickListener(onClickListener: OnClickListener?) {
        super.setOnClickListener { view ->
            onClickListener?.let { listener ->
                if (mDoubleClick) listener.onClick(view)
                else if (!mIsClicked) {
                    listener.onClick(view)
                    mIsClicked = true
                    Handler(Looper.getMainLooper()).postDelayed(
                        { mIsClicked = false },
                        mClickInterval.toLong()
                    )
                }
            }
        }
    }
}