package com.vmmontes.excurrency

import com.vmmontes.excurrency.domain.GetHistoryUseCaseContract
import com.vmmontes.excurrency.domain.ProvideHistoryRangeDatesUseCaseContract
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GraphicPresenterUnitTest {

    lateinit var presenter: GraphicPresenter
    lateinit var mockGetHistoryUseCase: GetHistoryUseCaseContract
    lateinit var mockProvideHistoryRangeDatesUseCase: ProvideHistoryRangeDatesUseCaseContract
    lateinit var view: GraphicView

    @Before
    fun setup() {
        mockGetHistoryUseCase = Mockito.mock(GetHistoryUseCaseContract::class.java)
        mockProvideHistoryRangeDatesUseCase = Mockito.mock(ProvideHistoryRangeDatesUseCaseContract::class.java)
        presenter = GraphicPresenter(mockGetHistoryUseCase, mockProvideHistoryRangeDatesUseCase)
        view = Mockito.mock(GraphicView::class.java)
    }

    @Test
    fun validateCallSetViewForStartDateAndEndDateFromonViewCreated() {
        val dateInMiliseconds = 1553803626367
        val dateInString = "28/03/2019"
        Mockito.`when`(mockProvideHistoryRangeDatesUseCase.getStartDate()).thenReturn(0)
        Mockito.`when`(mockProvideHistoryRangeDatesUseCase.getEndDate()).thenReturn(dateInMiliseconds)

        presenter.onAttach(view)
        presenter.onViewCreated()

        Mockito.verify(view, never()).showSelectedStartDate("")
        Mockito.verify(view, times(1)).showSelectedEndDate(dateInString)
    }

    @Test
    fun verifyisValidDatesIsWorking() {
        //28/03/2019
        val endDateInMiliseconds = 1553803626367
        //11/01/2019
        val starDateInMiliseconds = 1547161200000

        assertFalse(presenter.isValidDates(starDateInMiliseconds, 0))
        assertFalse(presenter.isValidDates(0, endDateInMiliseconds))
        assertFalse(presenter.isValidDates(endDateInMiliseconds, starDateInMiliseconds))
        assertTrue(presenter.isValidDates(starDateInMiliseconds, endDateInMiliseconds))
    }

    @Test
    fun verifyCalledViewsWhenUserClickOnDatesSelectors() {
        presenter.onAttach(view)

        presenter.onStartDateClicked()
        Mockito.verify(view, times(1)).openDataSelectorToStartDate()

        presenter.onEndDateClicked()
        Mockito.verify(view, times(1)).openDataSelectorToEndDate()
    }

    @Test
    fun verifyThrowShowDatesErrorWhenUserClickOnFindButton() {
        Mockito.`when`(mockProvideHistoryRangeDatesUseCase.getStartDate()).thenReturn(0)
        Mockito.`when`(mockProvideHistoryRangeDatesUseCase.getEndDate()).thenReturn(0)

        presenter.onAttach(view)

        presenter.onSearchButtonClicked()
        Mockito.verify(view, times(1)).showDatesError()

    }
}
