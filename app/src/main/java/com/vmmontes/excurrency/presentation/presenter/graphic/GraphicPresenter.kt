package com.vmmontes.excurrency.presentation.presenter.graphic

import com.vmmontes.excurrency.domain.GetHistoryUseCaseContract
import com.vmmontes.excurrency.domain.ProvideHistoryRangeDatesUseCaseContract
import com.vmmontes.excurrency.kernel.DOMAIN_DATE_FORMAT
import com.vmmontes.excurrency.kernel.coroutines.backgroundContext
import com.vmmontes.excurrency.kernel.presenter.CoroutinesPresenter
import com.vmmontes.excurrency.presentation.ui.graphic.GraphicView
import com.vmmontes.excurrency.presentation.utils.getDateTime
import com.vmmontes.excurrency.presentation.utils.getTimeDateInStringFormat
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GraphicPresenter(
    val getHistoryUseCase : GetHistoryUseCaseContract,
    val provideHistoryRangeDatesUseCase : ProvideHistoryRangeDatesUseCaseContract) : CoroutinesPresenter<GraphicView>() {

    fun onViewCreated() {
        if (provideHistoryRangeDatesUseCase.getStartDate() != null) {
            val startDateText = getTimeDateInStringFormat(provideHistoryRangeDatesUseCase.getStartDate()!!, DOMAIN_DATE_FORMAT)
            view?.showSelectedStartDate(startDateText)
        }

        if (provideHistoryRangeDatesUseCase.getEndDate() != null ) {
            val endDateText = getTimeDateInStringFormat(provideHistoryRangeDatesUseCase.getEndDate()!!, DOMAIN_DATE_FORMAT)
            view?.showSelectedEndDate(endDateText)
        }

        if(isValidDates(provideHistoryRangeDatesUseCase.getStartDate(),
                provideHistoryRangeDatesUseCase.getEndDate())) {
            getHistory()
        }
    }

    fun getHistory() {

        view?.showLoading()
        launch {
            async(backgroundContext) {
                getHistoryUseCase.execute(provideHistoryRangeDatesUseCase.getStartDate()!!,
                    provideHistoryRangeDatesUseCase.getEndDate()!!)
            }.await().run {
                view?.hideLoading()

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
        provideHistoryRangeDatesUseCase.setStartDate(getDateTime(dateFormatted, DOMAIN_DATE_FORMAT))
        view?.showSelectedStartDate(dateFormatted)
    }

    fun endDateSelected(day : String, month : String, year : String) {
        val dateFormatted = getViewDateFormat(day, month, year)
        provideHistoryRangeDatesUseCase.setEndDate(getDateTime(dateFormatted, DOMAIN_DATE_FORMAT))
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
        if(isValidDates(provideHistoryRangeDatesUseCase.getStartDate(),
                provideHistoryRangeDatesUseCase.getEndDate())) {
            getHistory()
        } else {
            view?.showDatesError()
        }
    }


}