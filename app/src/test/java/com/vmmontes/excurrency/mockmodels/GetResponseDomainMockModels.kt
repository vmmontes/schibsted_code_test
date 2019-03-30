package com.vmmontes.excurrency.mockmodels

import com.vmmontes.excurrency.domain.model.HistoryDayDomainModel
import com.vmmontes.excurrency.domain.model.HistoryDomainModel
import com.vmmontes.excurrency.domain.model.ResultHistoryDomainModel

class GetResponseDomainMockModels {

    fun getResultHistoryDomainModelWithSuccesResponseAndNullHistoryDomainModel(): ResultHistoryDomainModel =
            ResultHistoryDomainModel(null, true)

    fun getResultHistoryDomainModelWithSuccesFailResponseAndNullHistoryDomainModel(): ResultHistoryDomainModel =
        ResultHistoryDomainModel(null, false)

    fun getResultHistoryDomainModelWithSuccesResponseAndEmptyListInHistoryDomainModel(): ResultHistoryDomainModel =
        ResultHistoryDomainModel(HistoryDomainModel(ArrayList()), true)

    fun getResultHistoryDomainModelSuccesFailResponseAndEmptyListInHistoryDomainModel(): ResultHistoryDomainModel =
        ResultHistoryDomainModel(HistoryDomainModel(ArrayList()), false)

    fun getResultHistoryDomainModelSuccesResponseAndDataListinHistoryDomainModel(): ResultHistoryDomainModel {
        val listDomainModel = ArrayList<HistoryDayDomainModel>()
        val historyDayDomainModel = HistoryDayDomainModel("2019-03-30", "0.3")
        listDomainModel.add(historyDayDomainModel)

        return ResultHistoryDomainModel(HistoryDomainModel(listDomainModel), true)
    }

}