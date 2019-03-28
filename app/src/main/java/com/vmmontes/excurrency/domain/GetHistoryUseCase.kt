package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.domain.model.HistoryDomainModel

class GetHistoryUseCase(val historyRepository : HistoryRepository) : GetHistoryUseCaseContract {

    override fun execute(startDate : Long, endDate : Long) : HistoryDomainModel = historyRepository.get(startDate, endDate)
}

interface GetHistoryUseCaseContract {
    fun execute(startDate : Long, endDate : Long) : HistoryDomainModel
}