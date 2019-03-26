package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.domain.model.HistoricalExchangeDomainModel

interface HistoricalExchangeRepository {
    fun get() : ArrayList<HistoricalExchangeDomainModel>
    fun getAllStoredHistory() : ArrayList<HistoricalExchangeDomainModel>
}