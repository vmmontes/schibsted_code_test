package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.domain.model.HistoryDomainModel

interface HistoryRepository {
    fun get(startDate : Long, endDate : Long) : HistoryDomainModel
    fun getAllStoredHistory() : ArrayList<HistoryDomainModel>
}