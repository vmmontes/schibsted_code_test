package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.domain.model.HistoricalExchangeDomainModel

class HIstoricalExchangeRepositoryImp : HistoricalExchangeRepository {

    override fun get(): ArrayList<HistoricalExchangeDomainModel> {
        return ArrayList()
    }

    override fun getAllStoredHistory(): ArrayList<HistoricalExchangeDomainModel> {
        return ArrayList()
    }
}