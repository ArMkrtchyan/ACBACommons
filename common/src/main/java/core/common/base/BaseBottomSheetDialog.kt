package core.common.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import core.common.R
import core.common.shared.Inflater

abstract class BaseBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment() {
    protected abstract val inflate: Inflater<VB>
    abstract fun VB.initView()
    protected open val draggable: Boolean = false
    protected open val cancelable: Boolean = true
    protected lateinit var mActivity: BaseActivity<*>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
        }
    }

    @SuppressLint("ResourceType")
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog =
        BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = draggable
        }

    private lateinit var _binding: VB
    protected val mBinding: VB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        if (!::_binding.isInitialized) {
            _binding = inflate(inflater, container, false)
            _binding.initView()
        }
        isCancelable = cancelable
        return _binding.root
    }

    protected fun onScreenHeightChange(onChange: (Int) -> Unit) {
        val mRootWindow = requireActivity().window
        val mRootView: View = mRootWindow.decorView.findViewById(android.R.id.content)
        mRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            val view: View = mRootWindow.decorView
            view.getWindowVisibleDisplayFrame(r)
            // r.left, r.top, r.right, r.bottom
            onChange.invoke(mRootView.height - r.bottom)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        mActivity.hideSoftInput()
        super.onDismiss(dialog)
    }
}