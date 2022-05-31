package com.github.armkrtchyan.data.mappers

import com.github.armkrtchyan.data.IMapper
import com.github.armkrtchyan.data.responseModel.RatesResponseModel
import com.github.armkrtchyan.domain.models.RatesDomainModel

class RatesMapper : IMapper<RatesResponseModel, RatesDomainModel> {
    override fun map(model: RatesResponseModel): RatesDomainModel {
        return RatesDomainModel(
            rates = RatesDomainModel.Rates(
                last_update_date = model.rates?.last_update_date ?: "",
                cash = model.rates?.cash?.map {
                    RatesDomainModel.Rate(
                        buy = it.Buy ?: "",
                        sell = it.Sell ?: "",
                        centralBank = it.CB ?: "",
                        currency = it.Currency ?: "",
                    )
                } ?: arrayListOf(),
                card = model.rates?.cash?.map {
                    RatesDomainModel.Rate(
                        buy = it.Buy ?: "",
                        sell = it.Sell ?: "",
                        centralBank = it.CB ?: "",
                        currency = it.Currency ?: "",
                    )
                } ?: arrayListOf(),
                non_cash = model.rates?.cash?.map {
                    RatesDomainModel.Rate(
                        buy = it.Buy ?: "",
                        sell = it.Sell ?: "",
                        centralBank = it.CB ?: "",
                        currency = it.Currency ?: "",
                    )
                } ?: arrayListOf(),
                cross = model.rates?.cash?.map {
                    RatesDomainModel.Rate(
                        buy = it.Buy ?: "",
                        sell = it.Sell ?: "",
                        centralBank = it.CB ?: "",
                        currency = it.Currency ?: "",
                    )
                } ?: arrayListOf(),
                currencies = model.rates?.currencies?.map {
                    RatesDomainModel.Currencies(
                        key = it.Key ?: "",
                        value = it.Value ?: "",
                    )
                } ?: arrayListOf(),
            )
        )
    }
}