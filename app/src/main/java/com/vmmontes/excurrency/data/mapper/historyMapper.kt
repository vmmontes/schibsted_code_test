package com.vmmontes.excurrency.data.mapper

import com.vmmontes.exchange.model.history.ResultHistoryApiModel
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

fun toDomain(resultHistoryApiModel : ResultHistoryApiModel) : ResultHistoryDomainModel {
    val list = ArrayList<HistoryDayDomainModel>()
    val historyResponseApiModel = resultHistoryApiModel.historyResponseApiModel

    if (historyResponseApiModel != null) {
        for ((day, currency) in historyResponseApiModel.rates) {
            val historyDayDomainModel = HistoryDayDomainModel(day, currency.EUR)
            list.add(historyDayDomainModel)
        }
    }

    return ResultHistoryDomainModel(HistoryDomainModel(list), resultHistoryApiModel.requestIsSucces)
}