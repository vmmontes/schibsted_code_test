package com.vmmontes.excurrency

import com.vmmontes.excurrency.domain.GetHistoryUseCase
import com.vmmontes.excurrency.domain.GetHistoryUseCaseContract
import com.vmmontes.excurrency.domain.ProvideHistoryRangeDatesUseCaseContract
import com.vmmontes.excurrency.presentation.presenter.graphic.GraphicPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

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
    fun errorIfViewIsNotAttached(){
        presenter.onViewCreated()
    }
}
