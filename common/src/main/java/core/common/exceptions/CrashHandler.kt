package core.common.exceptions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.RetentionManager
import core.common.exceptions.exceptionhandler.ErrorModel
import core.common.exceptions.exceptionhandler.ExceptionActivity
import core.common.shared.extensions.getDeviceId

class CrashHandler(
    private val defaultHandler: Thread.UncaughtExceptionHandler? = null, private val applicationContext: Context
) : Thread.UncaughtExceptionHandler {

    private val chuckerCollector: ChuckerCollector by lazy {
        ChuckerCollector(
            context = applicationContext,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.FOREVER
        )
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        chuckerCollector.onError("error", throwable)
        val pair = try {
            val pInfo: PackageInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
            Pair(pInfo.versionName, pInfo.versionCode)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            Pair("unavailable", 0)
        }

        val s =
            "throwable -> ${throwable.message}\n" + "manufacture -> ${Build.MANUFACTURER}\n" + "deviceModel -> ${Build.MODEL}\n" + "versionName -> ${pair.first}\n" + "versionCode -> ${pair.second}\n" + "androidVersionCode -> ${Build.VERSION.SDK_INT}\n" + "deviceId -> ${
                applicationContext.getDeviceId()
            }\n" + "threadName -> ${thread.name}\n" + "stackTrace -> ${throwable.stackTraceToString()}"

        val model = ErrorModel()
        model.text = s
        model.manufacture = Build.MANUFACTURER
        model.deviceModel = Build.MODEL

        val stackTrace = throwable.cause?.stackTrace
        if (!stackTrace.isNullOrEmpty()) {
            model.className = stackTrace.firstOrNull()?.className
            model.crashLine = stackTrace.firstOrNull()?.lineNumber
        }
        val intent = Intent(applicationContext, ExceptionActivity::class.java)
        intent.putExtra("model", model)
        intent.putExtra("packageName", applicationContext.packageName)
        intent.putExtra("apiA", getApi().first)
        intent.putExtra("apiB", getApi().second)
        intent.putExtra("apiC", getApi().third)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        applicationContext.startActivity(intent)
        defaultHandler?.uncaughtException(thread, throwable)
    }

    private fun getApi(): Triple<String?, String?, String?> {
        return Triple("TAURQFPC2", "B04KABCP056", "jlkbj6nSCY3mWHmt1mDo0R4f")
    }
}