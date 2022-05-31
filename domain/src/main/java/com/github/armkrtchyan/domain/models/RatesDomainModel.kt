package com.github.armkrtchyan.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatesDomainModel(val rates: Rates) : Parcelable {
    @Parcelize
    data class Rates(
        val last_update_date: String,
        val cash: List<Rate>,
        val non_cash: List<Rate>,
        val card: List<Rate>,
        val cross: List<Rate>,
        val currencies: List<Currencies>,
    ) : Parcelable

    @Parcelize
    data class Rate(
        val buy: String,
        val sell: String,
        val centralBank: String,
        val currency: String,
    ) : Parcelable

    @Parcelize
    data class Currencies(
        val key: String,
        val value: String,
    ) : Parcelable
}