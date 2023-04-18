package core.common.shared.alertDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import core.common.databinding.LayoutLoadingBinding

class LoadingAlertDialog(
    context: Context,
) : Dialog(context) {

    private lateinit var mBinding: LayoutLoadingBinding

    private constructor(builder: Builder) : this(builder.context)

    companion object {
        inline fun build(required: Context, block: Builder.() -> Unit) = Builder(required).apply(block)
            .build().show()
    }

    data class Builder(val context: Context) {
        fun build(): LoadingAlertDialog {
            val v = LoadingAlertDialog(this)
            v.show()
            return v
        }
    }

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.requestFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutLoadingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setCancelable(false)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}