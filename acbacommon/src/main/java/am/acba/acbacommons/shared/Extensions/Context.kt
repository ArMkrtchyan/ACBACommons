package am.acba.acbacommons.shared.Extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(message: String?) {
    message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}

fun Context.showToast(@StringRes resId: Int) {
    showToast(resources.getString(resId))
}