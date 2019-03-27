package com.vmmontes.excurrency.presentation.presenter.graphic

import com.vmmontes.excurrency.domain.GetHistoryUseCase
import com.vmmontes.excurrency.kernel.coroutines.backgroundContext
import com.vmmontes.excurrency.kernel.presenter.CoroutinesPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import com.vmmontes.excurrency.presentation.utils.getDateTime
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GraphicPresenter : CoroutinesPresenter<GraphicView>() {

    val dateFormat = "dd/MM/yyyy"
    val getHistoryUseCase = GetHistoryUseCase()

    var startDate : Long? = null
    var endDate : Long? = null

    fun getHistory() {
        launch {
            async(backgroundContext) {
                getHistoryUseCase.execute(startDate!!, endDate!!)
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

    private fun getViewDateFormat(day : String, month : String, year : String) : String {
        return "$day/$month/$year"
    }

    fun starDateSelected(day : String, month : String, year : String) {
        val dateFormatted = getViewDateFormat(day, month, year)
        startDate = getDateTime(dateFormatted, dateFormat)
        view?.showSelectedStartDate(dateFormatted)
    }

    fun endDateSelected(day : String, month : String, year : String) {
        val dateFormatted = getViewDateFormat(day, month, year)
        endDate = getDateTime(dateFormatted, dateFormat)
        view?.showSelectedEndDate(dateFormatted)
    }

    fun isValidDates(startDate : Long?, endDate : Long?) : Boolean {
        return if (startDate != null && endDate != null) {
            endDate > startDate
        } else {
            false
        }
    }

    fun onSearchButtonClicked() {
        if(isValidDates(startDate, endDate)) {
            getHistory()
        } else {
            view?.showDatesError()
        }
    }
}