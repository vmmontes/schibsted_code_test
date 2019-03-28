package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSource
import com.vmmontes.excurrency.data.datasource.local.HistoryLocalDataSource
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

class HistoryRepositoryImp (
    val historyCloudDataSource : HistoryCloudDataSource,
    val historyLocalDataSource: HistoryLocalDataSource
): HistoryRepository {

    override fun get(startDate : Long, endDate : Long): ResultHistoryDomainModel = historyCloudDataSource.get(startDate, endDate)

    override fun getMemoryStartDate(): Long? = historyLocalDataSource.getStartDate()


    override fun getMemoryEndDate(): Long? = historyLocalDataSource.getEndDate()

    override fun setMemoryStartDate(startDate: Long) {
        historyLocalDataSource.setStartDate(startDate)
    }

    override fun setMemoryEndDate(endDate: Long) {
        historyLocalDataSource.setEndDate(endDate)
    }
}