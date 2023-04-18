package core.common.exceptions.exceptionhandler

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ErrorModel(
    var text: String? = null, var className: String? = null, var crashLine: Int? = 0, var manufacture: String? = null, var deviceModel: String? = null
) : Parcelable