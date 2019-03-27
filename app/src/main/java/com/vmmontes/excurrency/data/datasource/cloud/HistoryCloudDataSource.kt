package com.vmmontes.excurrency.data.datasource.cloud

import com.vmmontes.excurrency.domain.model.HistoryDomainModel

interface HistoryCloudDataSource {
    fun get(startDate : Long, endDate : Long) : HistoryDomainModel
}