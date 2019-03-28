package com.vmmontes.excurrency.data.datasource.cloud

import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

interface HistoryCloudDataSource {
    fun get(startDate : Long, endDate : Long) : ResultHistoryDomainModel
}