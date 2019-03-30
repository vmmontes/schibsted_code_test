package com.vmmontes.excurrency.domain

import com.vmmontes.excurrency.data.repository.HistoryRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class ProvideHistoryRangeDatesUnitTest {

    companion object {
        private val FAKE_END_DATE_IN_MILISECONDS = 1553803626367
        private val FAKE_START_DATE_IN_MILISECONDS = 1547161200000
    }

    private lateinit var mockHistoryRepository : HistoryRepository
    private lateinit var provideHistoryRaangeUseCase: ProvideHistoryRangeDatesUseCase

    @Before
    fun setup() {
        mockHistoryRepository = Mockito.mock(HistoryRepository::class.java)
        provideHistoryRaangeUseCase = ProvideHistoryRangeDatesUseCase(mockHistoryRepository)
    }

    @Test
    fun shouldGetLongStartDate() {
        Mockito.`when`(mockHistoryRepository.getMemoryStartDate()).thenReturn(FAKE_START_DATE_IN_MILISECONDS)

        assertTrue(provideHistoryRaangeUseCase.getStartDate() == FAKE_START_DATE_IN_MILISECONDS)

        verify(mockHistoryRepository, times(1)).getMemoryStartDate()
    }

    @Test
    fun shouldGetLongEndDate() {
        Mockito.`when`(mockHistoryRepository.getMemoryEndDate()).thenReturn(FAKE_END_DATE_IN_MILISECONDS)

        assertTrue(provideHistoryRaangeUseCase.getEndDate() == FAKE_END_DATE_IN_MILISECONDS)

        verify(mockHistoryRepository, times(1)).getMemoryEndDate()
    }

    @Test
    fun shouldSetLongStartDate() {
        provideHistoryRaangeUseCase.setStartDate(FAKE_START_DATE_IN_MILISECONDS)

        verify(mockHistoryRepository, times(1)).setMemoryStartDate(FAKE_START_DATE_IN_MILISECONDS)
    }

    @Test
    fun shouldSetLongEndDate() {
        provideHistoryRaangeUseCase.setEndDate(FAKE_END_DATE_IN_MILISECONDS)

        verify(mockHistoryRepository, times(1)).setMemoryEndDate(FAKE_END_DATE_IN_MILISECONDS)
    }
}
