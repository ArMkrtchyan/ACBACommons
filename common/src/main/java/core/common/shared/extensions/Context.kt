package core.common.shared.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import core.common.base.BaseActivity
import core.common.base.BaseFragment
import core.common.constants.BundleKeys

fun Context.showToast(message: String?) {
    message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}

fun Context.showToast(@StringRes resId: Int) {
    showToast(resources.getString(resId))
}

inline fun <reified T> Context.getByResourceId(@IdRes resId: Int): T {
    return when (T::class) {
        Drawable::class -> ContextCompat.getDrawable(this, resId) as T
        Int::class -> ContextCompat.getColor(this, resId) as T
        ColorStateList::class -> ContextCompat.getColorStateList(this, resId) as T
        String::class -> this.resources.getString(resId) as T
        CharSequence::class -> this.resources.getString(resId) as T
        else -> throw IllegalArgumentException("${T::class.java.simpleName} is not supported")
    }
}

inline fun <reified T : BaseActivity<*>> BaseActivity<*>.launch(bundle: Bundle? = null, finish: Boolean = false, finishAll: Boolean = false, enterAnim: Int = 0, exitAnim: Int = 0) {
    startActivity(Intent(this, T::class.java).apply {
        bundle?.let { putExtra(BundleKeys.BUNDLE, it) }
        if (finishAll) addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    })
    if (finish) finish()
    overridePendingTransition(enterAnim, exitAnim)
}

inline fun <reified T : BaseActivity<*>> BaseFragment.launch(bundle: Bundle? = null, finish: Boolean = false, finishAll: Boolean = false, enterAnim: Int = 0, exitAnim: Int = 0) {
    (requireActivity() as BaseActivity<*>).launch<T>(bundle, finish, finishAll, enterAnim, exitAnim)
}

fun Context.getActionBarHeight(): Int {
    var actionBarHeight = 0
    val tv = TypedValue()
    if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return actionBarHeight
}

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)
fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(this.context)
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getDeviceId(): String {
    return android.provider.Settings.Secure.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID)
}

fun Context.getAppVersionName(): String? {
    try {
        return packageManager.getPackageInfo(packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return null
}