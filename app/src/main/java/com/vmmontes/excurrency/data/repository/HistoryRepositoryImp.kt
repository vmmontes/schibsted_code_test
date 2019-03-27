package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSource
import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSourceImp
import com.vmmontes.excurrency.domain.model.HistoryDomainModel

class HistoryRepositoryImp : HistoryRepository {
    val historyCloudDataSource : HistoryCloudDataSource = HistoryCloudDataSourceImp()

    override fun get(): HistoryDomainModel = historyCloudDataSource.get()

    override fun getAllStoredHistory(): ArrayList<HistoryDomainModel> {
        return ArrayList()
    }
}