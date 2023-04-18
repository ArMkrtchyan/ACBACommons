package core.common.shared.extensions

import android.util.Log
import core.common.BuildConfig

inline fun <reified T> T.log(tag: String = "Common", prefix: String = ""): T {
    if (BuildConfig.DEBUG)
        Log.i(tag, prefix + this.toString())
    return this
}