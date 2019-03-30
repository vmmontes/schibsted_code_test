package com.vmmontes.excurrency.data.repository

import com.vmmontes.excurrency.data.datasource.cloud.HistoryCloudDataSource
import com.vmmontes.excurrency.data.datasource.local.HistoryLocalDataSource
import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class HistoryRepositoryUnitTest {

    companion object {
        private val FAKE_END_DATE_IN_MILISECONDS = 1553803626367
        private val FAKE_START_DATE_IN_MILISECONDS = 1547161200000
    }

    private lateinit var mockHistoryCloudDataSource : HistoryCloudDataSource
    private lateinit var mockHistoryLocalDataSource: HistoryLocalDataSource
    private lateinit var historyRepository: HistoryRepository

    @Before
    fun setup() {
        mockHistoryCloudDataSource = Mockito.mock(HistoryCloudDataSource::class.java)
        mockHistoryLocalDataSource = Mockito.mock(HistoryLocalDataSource::class.java)
        historyRepository = HistoryRepositoryImp(mockHistoryCloudDataSource, mockHistoryLocalDataSource)
    }

    @Test
    fun shouldGetResultHistoryDomainModelFromGetMethod() {
        Mockito.`when`(mockHistoryCloudDataSource.get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)).thenReturn(createResultHistoryDomainModel())

        historyRepository.get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)

        verify(mockHistoryCloudDataSource, times(1)).get(FAKE_START_DATE_IN_MILISECONDS, FAKE_END_DATE_IN_MILISECONDS)
    }

    @Test
    fun shouldGetLongStartDate() {
        Mockito.`when`(mockHistoryLocalDataSource.getStartDate()).thenReturn(FAKE_START_DATE_IN_MILISECONDS)

        assertTrue(historyRepository.getMemoryStartDate() == FAKE_START_DATE_IN_MILISECONDS)

        verify(mockHistoryLocalDataSource, times(1)).getStartDate()
    }

    @Test
    fun shouldGetLongEndDate() {
        Mockito.`when`(mockHistoryLocalDataSource.getEndDate()).thenReturn(FAKE_END_DATE_IN_MILISECONDS)

        assertTrue(historyRepository.getMemoryEndDate() == FAKE_END_DATE_IN_MILISECONDS)

        verify(mockHistoryLocalDataSource, times(1)).getEndDate()
    }

    @Test
    fun shouldSetLongStartDate() {
        historyRepository.setMemoryStartDate(FAKE_START_DATE_IN_MILISECONDS)

        verify(mockHistoryLocalDataSource, times(1)).setStartDate(FAKE_START_DATE_IN_MILISECONDS)
    }

    @Test
    fun shouldSetLongEndDate() {
        historyRepository.setMemoryEndDate(FAKE_END_DATE_IN_MILISECONDS)

        verify(mockHistoryLocalDataSource, times(1)).setEndDate(FAKE_END_DATE_IN_MILISECONDS)
    }

    private fun createResultHistoryDomainModel() = ResultHistoryDomainModel(
        HistoryDomainModel(
            ArrayList<HistoryDayDomainModel>()
        ),
        true
    )
}
