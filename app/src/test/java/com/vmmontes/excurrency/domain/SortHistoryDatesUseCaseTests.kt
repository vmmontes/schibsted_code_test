package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import org.junit.Assert.assertTrue
import org.junit.Test

class SortHistoryDatesUseCaseTests {

    @Test
    fun validateGetHistoryUseCaseWorkflowWithRightData() {
        val list = ArrayList<HistoryDayDomainModel>()
        val firstDate = "01/01/2019"
        val secondDate = "29/02/2019"
        val thirdDate = "29/03/2019"
        val fourthDate = "30/03/2019"

        list.add(HistoryDayDomainModel(secondDate, "2.1"))
        list.add(HistoryDayDomainModel(firstDate, "2.1"))
        list.add(HistoryDayDomainModel(fourthDate, "2.1"))
        list.add(HistoryDayDomainModel(thirdDate, "2.1"))

        val historyDomainModel = HistoryDomainModel(list)
        val sortHistoryDatesUseCase = SortHistoryDatesUseCase()
        sortHistoryDatesUseCase.execute(historyDomainModel)

        assertTrue(historyDomainModel.history[0].day == firstDate)
        assertTrue(historyDomainModel.history[1].day == secondDate)
        assertTrue(historyDomainModel.history[2].day == thirdDate)
        assertTrue(historyDomainModel.history[3].day == fourthDate)

    }
}
