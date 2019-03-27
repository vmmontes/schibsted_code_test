package com.vmmontes.excurrency.data.mapper

import com.vmmontes.exchange.model.history.HistoryResponseApiModel
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel

fun toDomain(historyResponseApiModel : HistoryResponseApiModel) : HistoryDomainModel {
    val list = ArrayList<HistoryDayDomainModel>()
    for ((day, currency) in historyResponseApiModel.rates) {
        val historyDayDomainModel = HistoryDayDomainModel(day, currency.EUR)
        list.add(historyDayDomainModel)
    }

    return HistoryDomainModel(list)
}