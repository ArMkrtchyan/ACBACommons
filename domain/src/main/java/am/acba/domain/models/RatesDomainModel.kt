package am.acba.domain.models

data class RatesDomainModel(val rates: Rates) {
    data class Rates(
        val last_update_date: String,
        val cash: List<Rate>,
        val non_cash: List<Rate>,
        val card: List<Rate>,
        val cross: List<Rate>,
        val currencies: List<Currencies>,
    )

    data class Rate(
        val buy: String,
        val sell: String,
        val centralBank: String,
        val currency: String,
    )

    data class Currencies(
        val key: String,
        val value: String,
    )
}