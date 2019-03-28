package com.vmmontes.excurrency.data.datasource.local

class HistoryLocalDataSourceImp : HistoryLocalDataSource{
    companion object {
        var startDateDataSource : Long = 0
            private set
        var endDateDataSource : Long = 0
            private set
    }

    override fun getStartDate(): Long = startDateDataSource

    override fun getEndDate(): Long = endDateDataSource

    override fun setStartDate(startDate: Long) {
        startDateDataSource = startDate
    }

    override fun setEndDate(endDate: Long) {
        endDateDataSource = endDate
    }
}

