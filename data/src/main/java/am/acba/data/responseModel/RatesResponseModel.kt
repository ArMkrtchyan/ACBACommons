package am.acba.data.responseModel

data class RatesResponseModel(val rates: Rates?) {
    data class Rates(
        val last_update_date: String?,
        val cash: List<Rate>,
        val non_cash: List<Rate>,
        val card: List<Rate>,
        val cross: List<Rate>,
        val currencies: List<Currencies>,
    )

    data class Rate(
        val Buy: String?,
        val Sell: String?,
        val CB: String?,
        val Currency: String?,
    )

    data class Currencies(
        val Key: String?,
        val Value: String?,
    )
}
