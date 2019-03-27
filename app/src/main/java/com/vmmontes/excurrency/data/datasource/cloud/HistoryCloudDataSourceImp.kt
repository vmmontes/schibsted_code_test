package com.vmmontes.excurrency.data.datasource.cloud

import com.vmmontes.exchange.client.HistoryClient
import com.vmmontes.exchange.client.HistoryClientImp
import com.vmmontes.excurrency.data.mapper.toDomain
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat

class HistoryCloudDataSourceImp : HistoryCloudDataSource {

    val historyClient: HistoryClient = HistoryClientImp()

    override fun get(startDate : Long, endDate : Long): HistoryDomainModel {
        val stringStartDate = getTimeDateInStringFormat(startDate, "yyyy-MM-dd")
        val stringEndDate = getTimeDateInStringFormat(endDate, "yyyy-MM-dd")

        return toDomain(historyClient.get(stringStartDate, stringEndDate))
    }
}