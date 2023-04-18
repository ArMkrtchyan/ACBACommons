package core.common.enum

import java.text.SimpleDateFormat
import java.util.*

enum class DatePatternsEnum(val pattern: String) {
    UTC("yyyy-MM-dd'T'HH:mm:ss"),
    DAY_MONTH_YEAR("dd MMMM, yyyy"),
    DAY_MONTH_YEAR_NUMBER("dd/MM/yyyy"),
    DAY_MONTH_YEAR_NUMBER_DOTS("dd.MM.yyyy"),
    DAY_MONTH_YEAR_NUMBER_HOUR("dd/MM/yyyy HH:mm"),
    DAY_MON_YEAR("dd-MMM-yyyy"),
    YEAR_MON_DAY("yyyy-MM-dd"),
    DAY_MONTH_YEAR_TIME_PATTERN("dd MMM yyyy HH:mm"),
    DAY_MONTH_YEAR_TIME_PATTERN2("dd.MM.yyyy HH:mm"),
    DAY_MONTH_TIME_PATTERN("d MMM HH:mm"),
    HOUR_MINUTE_TIME_PATTERN("HH:mm"),
    MOUNT_YEAR_PATTERN("MMM, yyyy"),
    WEEK_DAY_MOUNT_PATTERN("EEEE d MMM"),
    MOUNT_DAY_YEAR_PATTERN("MMMM d yyyy"),
    DAY_MONTH_SHORT("dd/MM"),
    YEAR("yyyy"),
    MONTH("MM"),
    SERVER_ISO_PATTERN("yyyy-MM-dd'T'HH:mm:ss"),
    SERVER_ISO_PATTERN_ZERO("yyyy-MM-dd'T'00:00:00");

    fun formatDateToPattern(date: Date?): String? {
        return try {
            SimpleDateFormat(pattern, Locale("hy")).apply { }.format(date)
        } catch (e: Exception) {
            null
        }
    }

    private fun formatDateToIso(date: Date?): String? {
        return try {
            SimpleDateFormat(SERVER_ISO_PATTERN.pattern, Locale("hy")).apply { }.format(date)
        } catch (e: Exception) {
            null
        }
    }

    fun formatIsoToPattern(date: String): String? {
        return try {
            formatDateToPattern(SERVER_ISO_PATTERN.formatPatternToDate(date))
        } catch (e: Exception) {
            null
        }
    }

    fun formatPatternToDate(date: String): Date? {
        return try {
            SimpleDateFormat(pattern, Locale("hy")).apply { timeZone = TimeZone.getTimeZone("UTC") }.parse(date)
        } catch (e: Exception) {
            null
        }
    }

    fun formatPatternToIso(date: String): String? {
        return try {
            formatDateToIso(formatPatternToDate(date))
        } catch (e: Exception) {
            null
        }
    }
}
