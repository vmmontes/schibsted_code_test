package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.domain.model.HistoryDomainModel

interface HistoryRepository {
    fun get() : HistoryDomainModel
    fun getAllStoredHistory() : ArrayList<HistoryDomainModel>
}