package com.vmmontes.excurrency.data.datasource.cloud

import com.vmmontes.exchange.client.HistoryClient
import com.vmmontes.exchange.client.HistoryClientImp
import com.vmmontes.excurrency.data.mapper.toDomain
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel
import com.vmmontes.excurrency.kernel.API_DATE_FORMAT
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat

class HistoryCloudDataSourceImp(val historyClient: HistoryClient) : HistoryCloudDataSource {

    override fun get(startDate : Long, endDate : Long): ResultHistoryDomainModel {
        val stringStartDate = getTimeDateInStringFormat(startDate, API_DATE_FORMAT)
        val stringEndDate = getTimeDateInStringFormat(endDate, API_DATE_FORMAT)

        return toDomain(historyClient.get(stringStartDate, stringEndDate))
    }
}