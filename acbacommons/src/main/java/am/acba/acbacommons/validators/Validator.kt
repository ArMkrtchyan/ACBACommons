package am.acba.acbacommons.validators

interface Validator {
    fun isRequiredForValidation(): Boolean
    fun isValid(): Boolean
}