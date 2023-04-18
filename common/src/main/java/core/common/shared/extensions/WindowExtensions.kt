package core.common.shared.extensions

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import core.common.base.BaseApplication

fun Window.handleWindowState(isFullScreen: Boolean = true, isLightStatusBar: Boolean = true, isLightNavBar: Boolean = true) {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
            if (isFullScreen)
                setDecorFitsSystemWindows(false)
            decorView.windowInsetsController?.apply {
                setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                setSystemBarsAppearance(APPEARANCE_LIGHT_NAVIGATION_BARS, APPEARANCE_LIGHT_NAVIGATION_BARS)
            }
            setCustomNavigationBarColor()
            setCustomNavigationBarDividerColor()
            setCustomStatusBarColor()
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
            setCustomNavigationBarColor()
            setCustomNavigationBarDividerColor()
            setCustomStatusBarColor()
            var flags = 0
            if (isFullScreen)
                flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or flags
            if (isLightStatusBar)
                flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or flags
            if (isLightNavBar)
                flags = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or flags

            decorView.systemUiVisibility = flags
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            setCustomStatusBarColor()
            setCustomNavigationBarColor(android.R.color.black)
            var flags = 0
            if (isFullScreen)
                flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or flags
            if (isLightStatusBar)
                flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or flags
            decorView.systemUiVisibility = flags
        }
        else -> {
            setCustomStatusBarColor(android.R.color.white)
        }
    }
}

private fun Window.setCustomStatusBarColor(color: Int = android.R.color.transparent) {
    statusBarColor = ContextCompat.getColor(BaseApplication.instance.applicationContext, color)
}

private fun Window.setCustomNavigationBarColor(color: Int = android.R.color.white) {
    navigationBarColor = ContextCompat.getColor(context, color)
}

@RequiresApi(Build.VERSION_CODES.P)
private fun Window.setCustomNavigationBarDividerColor(color: Int = android.R.color.transparent) {
    navigationBarDividerColor = ContextCompat.getColor(context, color)
}