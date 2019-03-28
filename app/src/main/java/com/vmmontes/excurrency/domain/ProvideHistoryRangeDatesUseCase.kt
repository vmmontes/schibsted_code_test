package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.data.repository.HistoryRepositoryImp

class ProvideHistoryRangeDatesUseCase(val historyRepository : HistoryRepository): ProvideHistoryRangeDatesUseCaseContract{

    override fun getStartDate() : Long? = historyRepository.getMemoryStartDate()

    override fun getEndDate() : Long? = historyRepository.getMemoryEndDate()

    override fun setStartDate(startDate : Long) {
        historyRepository.setMemoryStartDate(startDate)
    }

    override fun setEndDate(endDate : Long) {
        historyRepository.setMemoryEndDate(endDate)
    }
}

interface ProvideHistoryRangeDatesUseCaseContract {
    fun getStartDate() : Long?
    fun getEndDate() : Long?
    fun setStartDate(startDate : Long)
    fun setEndDate(endDate : Long)
}