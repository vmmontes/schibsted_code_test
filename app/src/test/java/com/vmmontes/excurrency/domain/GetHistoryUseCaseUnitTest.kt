package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel
import com.vmmontes.excurrency.mockmodels.GetResponseDomainMockModels
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times

class GetHistoryUseCaseUnitTest {

    companion object {
        private val FAKE_END_DATE_IN_MILISECONDS = 1553803626367
        private val FAKE_START_DATE_IN_MILISECONDS = 1547161200000
    }

    private lateinit var mockHistoryRepository : HistoryRepository
    private lateinit var mockSortHistoryDatesUseCase: SortHistoryDatesUseCaseContract
    private lateinit var getHistoryUseCase: GetHistoryUseCase

    @Before
    fun setup() {
        mockHistoryRepository = Mockito.mock(HistoryRepository::class.java)
        mockSortHistoryDatesUseCase = Mockito.mock(SortHistoryDatesUseCaseContract::class.java)
        getHistoryUseCase = GetHistoryUseCase(mockHistoryRepository, mockSortHistoryDatesUseCase)
    }

    @Test
    fun validateGetHistoryUseCaseWorkflowWithRightData() {
        val getResponseDomainModel = GetResponseDomainMockModels()
        val resultHistoryDomainModel = getResponseDomainModel.getResultHistoryDomainModelSuccesResponseAndDataListinHistoryDomainModel()

        Mockito.`when`(mockHistoryRepository.get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)).thenReturn(resultHistoryDomainModel)
        //Mockito.`when`(mockSortHistoryDatesUseCase.execute(resultHistoryDomainModel.historyDomainModel!!)).thenReturn(resultHistoryDomainModel.historyDomainModel)


        val result: ResultHistoryDomainModel = getHistoryUseCase.execute(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)
        Mockito.verify(mockHistoryRepository, times(1)).get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)
        Mockito.verify(mockSortHistoryDatesUseCase, times(1)).execute(resultHistoryDomainModel.historyDomainModel!!)

        assertTrue(result.isSucces)
    }

    @Test
    fun validateGetHistoryUseCaseWorkflowWithErrorResponse() {
        val getResponseDomainModel = GetResponseDomainMockModels()
        val resultHistoryDomainModel = getResponseDomainModel.getResultHistoryDomainModelSuccesFailResponseAndEmptyListInHistoryDomainModel()

        Mockito.`when`(mockHistoryRepository.get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)).thenReturn(resultHistoryDomainModel)

        val result: ResultHistoryDomainModel = getHistoryUseCase.execute(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)
        Mockito.verify(mockHistoryRepository, times(1)).get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)
        Mockito.verify(mockSortHistoryDatesUseCase, never()).execute(resultHistoryDomainModel.historyDomainModel!!)

        assertFalse(result.isSucces)
    }
}
