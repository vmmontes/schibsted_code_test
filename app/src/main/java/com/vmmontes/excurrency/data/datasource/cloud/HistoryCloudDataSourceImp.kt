package com.vmmontes.excurrency.data.datasource.cloud

import com.vmmontes.exchange.client.HistoryClient
import com.vmmontes.exchange.client.HistoryClientImp
import com.vmmontes.excurrency.data.mapper.toDomain
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel

class HistoryCloudDataSourceImp : HistoryCloudDataSource {

    val historyClient: HistoryClient = HistoryClientImp()

    override fun get(): HistoryDomainModel = toDomain(historyClient.get())
}