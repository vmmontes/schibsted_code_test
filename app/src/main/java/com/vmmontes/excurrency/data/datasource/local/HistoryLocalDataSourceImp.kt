package com.vmmontes.excurrency.data.datasource.local

class HistoryLocalDataSourceImp : HistoryLocalDataSource{
    companion object {
        var startDateDataSource : Long? = null
            private set
        var endDateDataSource : Long? = null
            private set
    }

    override fun getStartDate(): Long? = startDateDataSource

    override fun getEndDate(): Long? = endDateDataSource

    override fun setStartDate(startDate: Long) {
        startDateDataSource = startDate
    }

    override fun setEndDate(endDate: Long) {
        endDateDataSource = endDate
    }
}

