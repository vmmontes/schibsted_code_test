package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

interface HistoryRepository {
    fun get(startDate : Long, endDate : Long) : ResultHistoryDomainModel
    fun getMemoryStartDate() : Long?
    fun getMemoryEndDate() : Long?
    fun setMemoryStartDate(startDate: Long)
    fun setMemoryEndDate(endDate: Long)
}