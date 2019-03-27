package com.vmmontes.excurrency.presentation.ui.graphic

import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel

interface GraphicView {
    fun showError()
    fun showValuesIsEmpty()
    fun showValues(list : List<HistoryDayDomainModel>)
    fun openDataSelectorToStartDate()
    fun openDataSelectorToEndDate()
    fun showSelectedStartDate(date : String)
    fun showSelectedEndDate(date : String)
}