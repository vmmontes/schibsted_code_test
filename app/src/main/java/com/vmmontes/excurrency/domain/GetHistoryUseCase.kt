package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

class GetHistoryUseCase(
    val historyRepository : HistoryRepository,
    val sortHistoryDatesUseCase: SortHistoryDatesUseCaseContract) : GetHistoryUseCaseContract {

    override fun execute(startDate : Long, endDate : Long) : ResultHistoryDomainModel {
        val resultHistoryDomainModel = historyRepository.get(startDate, endDate)

        if (resultHistoryDomainModel.isSucces && resultHistoryDomainModel.historyDomainModel != null) {
            sortHistoryDatesUseCase.execute(resultHistoryDomainModel.historyDomainModel as HistoryDomainModel)
        }

        return resultHistoryDomainModel
    }
}

interface GetHistoryUseCaseContract {
    fun execute(startDate : Long, endDate : Long) : ResultHistoryDomainModel
}