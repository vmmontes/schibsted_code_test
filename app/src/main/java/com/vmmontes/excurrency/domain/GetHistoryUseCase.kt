package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.data.repository.HistoryRepositoryImp
import com.vmmontes.excurrency.domain.model.HistoryDomainModel

class GetHistoryUseCase {

    val historyRepository : HistoryRepository = HistoryRepositoryImp()

    fun execute() : HistoryDomainModel = historyRepository.get()
}