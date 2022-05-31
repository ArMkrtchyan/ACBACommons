package com.github.armkrtchyan.common.shared.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

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