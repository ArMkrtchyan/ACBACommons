package com.github.armkrtchyan.common.widgets

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

inline fun <reified T : View> Fragment.widget(): Lazy<T> {
    return lazy {
        when (T::class) {
            ButtonACBA::class.java -> ButtonACBA(requireContext()) as T
            else -> throw IllegalArgumentException("")
        }
    }
}

inline fun <reified T : View> Activity.widget(): Lazy<T> {
    return lazy {
        when (T::class) {
            ButtonACBA::class -> ButtonACBA(this) as T
            else -> throw IllegalArgumentException("")
        }
    }
}