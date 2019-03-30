package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.domain.model.HistoryDomainModel

class SortHistoryDatesUseCase: SortHistoryDatesUseCaseContract{

    override fun execute(historyDomainModel: HistoryDomainModel) {
        val history = historyDomainModel.history
        val sortedList = history.sortedBy { it.day }

        historyDomainModel.history = sortedList
    }
}

interface SortHistoryDatesUseCaseContract {
    fun execute(historyDomainModel: HistoryDomainModel)
}