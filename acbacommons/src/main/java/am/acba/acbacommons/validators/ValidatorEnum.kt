package am.acba.acbacommons.validators

import java.util.regex.Pattern

enum class ValidatorEnum(private val pattern: Pattern,val errorMessage:String) {
    NONE(Pattern.compile(""),""),
    EMAIL(Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"),"Invalid email"),
    NAME(Pattern.compile("^[\\p{L}.-]{3,30}$"),"Invalid name"),
    PASSWORD(Pattern.compile(".{6,20}"),"invalid password");

    fun isMatch(value: CharSequence?): Boolean {
        return pattern.matcher((value ?: "").trim()).matches()
    }
}