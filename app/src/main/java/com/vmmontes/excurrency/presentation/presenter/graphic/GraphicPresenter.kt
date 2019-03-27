package com.vmmontes.excurrency.presentation.presenter.graphic

import com.vmmontes.excurrency.domain.GetHistoryUseCase
import com.vmmontes.excurrency.kernel.coroutines.backgroundContext
import com.vmmontes.excurrency.kernel.presenter.CoroutinesPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GraphicPresenter : CoroutinesPresenter<GraphicView>() {

    val getHistoryUseCase = GetHistoryUseCase()

    fun onViewStart() {
        launch {
            async(backgroundContext) {
                getHistoryUseCase.execute()
            }.await().run {
                if (this.history.isEmpty()) {
                    view?.showValuesIsEmpty()
                } else {
                    val sortedList = this.history.sortedBy { it.day }
                    view?.showValues(sortedList)


                }
            }
        }
    }

    fun onStartDateClicked() {
        view?.openDataSelectorToStartDate()
    }

    fun onEndDateClicked() {
        view?.openDataSelectorToEndDate()
    }

    fun starDateSelected(day : String, month : String, year : String) {

    }

    fun endDateSelected(day : String, month : String, year : String) {

    }
}