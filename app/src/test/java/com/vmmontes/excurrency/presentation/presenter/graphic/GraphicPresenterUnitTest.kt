package com.vmmontes.excurrency.presentation.presenter.graphic

import com.vmmontes.excurrency.domain.GetHistoryUseCaseContract
import com.vmmontes.excurrency.domain.ProvideHistoryRangeDatesUseCaseContract
import com.vmmontes.excurrency.mockmodels.GetResponseDomainMockModels
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times

class GraphicPresenterUnitTest {

    private lateinit var presenter: GraphicPresenter
    private lateinit var mockGetHistoryUseCase: GetHistoryUseCaseContract
    private lateinit var mockProvideHistoryRangeDatesUseCase: ProvideHistoryRangeDatesUseCaseContract
    private lateinit var view: GraphicView

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
    fun verifyisValidDatesMethodCallRightMethodsOfView() {
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

    @Test
    fun verifyWhenGetHistoryUseCaseReturnResponseMethodWhenResponseHasNullHistory() {
        val getGraphicPresenterMockModels = GetResponseDomainMockModels()
        presenter.onAttach(view)

        presenter.whenGetHistoryUseCaseReturnResponse(getGraphicPresenterMockModels.getResultHistoryDomainModelWithSuccesResponseAndNullHistoryDomainModel())
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).showError()
        Mockito.verify(view, never()).showValuesIsEmpty()
        Mockito.verify(view, never()).showValues(ArrayList())
    }

    @Test
    fun verifyWhenGetHistoryUseCaseReturnResponseMethodWhenResponseFailsAndNullHistory() {
        val getGraphicPresenterMockModels = GetResponseDomainMockModels()
        presenter.onAttach(view)

        presenter.whenGetHistoryUseCaseReturnResponse(getGraphicPresenterMockModels.getResultHistoryDomainModelWithSuccesFailResponseAndNullHistoryDomainModel())
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).showError()
        Mockito.verify(view, never()).showValuesIsEmpty()
        Mockito.verify(view, never()).showValues(ArrayList())
    }

    @Test
    fun verifyWhenGetHistoryUseCaseReturnResponseMethodWhenResponseIsSuccesAndHasEmptyHistoryList() {
        val getGraphicPresenterMockModels = GetResponseDomainMockModels()
        presenter.onAttach(view)

        presenter.whenGetHistoryUseCaseReturnResponse(getGraphicPresenterMockModels.getResultHistoryDomainModelWithSuccesResponseAndEmptyListInHistoryDomainModel())
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, never()).showError()
        Mockito.verify(view, times(1)).showValuesIsEmpty()
        Mockito.verify(view, never()).showValues(ArrayList())
    }

    @Test
    fun verifyWhenGetHistoryUseCaseReturnResponseMethodWhenResponseFailsAndHasEmptyHistoryList() {
        val getGraphicPresenterMockModels = GetResponseDomainMockModels()
        presenter.onAttach(view)

        presenter.whenGetHistoryUseCaseReturnResponse(getGraphicPresenterMockModels.getResultHistoryDomainModelSuccesFailResponseAndEmptyListInHistoryDomainModel())
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).showError()
        Mockito.verify(view, never()).showValuesIsEmpty()
        Mockito.verify(view, never()).showValues(ArrayList())
    }

    @Test
    fun verifyWhenGetHistoryUseCaseReturnResponseMethodWhenResponseIsSuccesAndHasHistoryList() {
        val getGraphicPresenterMockModels = GetResponseDomainMockModels()
        presenter.onAttach(view)

        val resultHistoryDomainModel = getGraphicPresenterMockModels.getResultHistoryDomainModelSuccesResponseAndDataListinHistoryDomainModel()
        presenter.whenGetHistoryUseCaseReturnResponse(resultHistoryDomainModel)
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, never()).showError()
        Mockito.verify(view, never()).showValuesIsEmpty()
        Mockito.verify(view, times(1)).showValues(resultHistoryDomainModel.historyDomainModel!!.history)
    }

    @Test
    fun validateIfEndAndStartDateSelectedIsShowedRitghtIntoView() {
        presenter.onAttach(view)

        presenter.endDateSelected("30", "03", "2019")
        Mockito.verify(view, times(1)).showSelectedEndDate("30/03/2019")

        presenter.starDateSelected("25", "03", "2019")
        Mockito.verify(view, times(1)).showSelectedStartDate("25/03/2019")
    }

    @Test
    fun validateViewsCallWhenUserClickOnView() {
        presenter.onAttach(view)

        presenter.onStartDateClicked()
        Mockito.verify(view, times(1)).openDataSelectorToStartDate()

        presenter.onEndDateClicked()
        Mockito.verify(view, times(1)).openDataSelectorToEndDate()
    }
}
