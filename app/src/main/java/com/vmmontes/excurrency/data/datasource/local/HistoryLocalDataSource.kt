package com.vmmontes.excurrency.data.datasource.local

interface HistoryLocalDataSource {
    fun getStartDate() : Long?
    fun getEndDate() : Long?
    fun setStartDate(startDate : Long)
    fun setEndDate(endDate : Long)
}

