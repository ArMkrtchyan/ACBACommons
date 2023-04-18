package core.common.shared.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.format(): String {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    return formatter.format(this)
}

fun Double.formatNumberOrEmpty(): String {
    return if (this > 0.0) this.format() else ""
}

fun Double.formatToLong(): String {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 0
    formatter.minimumFractionDigits = 0
    return formatter.format(this)
}

fun String.formatLongToString(): Long {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 0
    formatter.minimumFractionDigits = 0
    return try {
        (formatter.parse(this)?.toLong() ?: 0).log("FormatterTag")
    } catch (e: Exception) {
        0
    }
}

fun String.formatLongStringToString(): String {
    return try {
        val formatter = NumberFormat.getInstance(Locale.ENGLISH)
        formatter.maximumFractionDigits = 0
        formatter.minimumFractionDigits = 0
        formatter.format(this.formatStringToLong())
    } catch (e: Exception) {
        ""
    }
}

fun String.formatStringToDouble(): Double {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 1
    formatter.minimumFractionDigits = 1
    return try {
        (formatter.parse(this)?.toDouble() ?: 0.0).log("FormatterTag")
    } catch (e: Exception) {
        0.0
    }
}

fun String.formatStringToDoubleOrNull(): Double {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 1
    formatter.minimumFractionDigits = 1
    return try {
        (formatter.parse(this)?.toDouble() ?: 0.0).log("FormatterTag")
    } catch (e: Exception) {
        0.0
    }
}

fun String.formatStringToInt(): Int {
    return try {
        this.toInt().log("FormatterTag")
    } catch (e: Exception) {
        0
    }
}

fun String.formatStringToLong(): Long {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 0
    formatter.minimumFractionDigits = 0
    return try {
        (formatter.parse(this)?.toLong() ?: 0).log("FormatterTag")
    } catch (e: Exception) {
        0
    }
}

fun String.formatStringToIntOrNull(): Int? {
    return try {
        this.toInt().log("FormatterTag")
    } catch (e: Exception) {
        null
    }
}

fun Double.formatToString(): String {
    val formatter = DecimalFormat("#.00")
    return formatter.format(this).replace(",", ".")
}


fun Double.formatPercent(): String {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    formatter.maximumFractionDigits = 1
    formatter.minimumFractionDigits = 1
    return formatter.format(this) + " %"
}