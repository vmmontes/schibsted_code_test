package com.vmmontes.exchange.client

import com.vmmontes.exchange.RetrofitService
import com.vmmontes.exchange.model.history.HistoryResponseApiModel

class HistoryClientImp: HistoryClient {

    override fun get(startDate : String, endDate : String): HistoryResponseApiModel {

        val response = RetrofitService.getService().getHistory(startDate, endDate).execute()

        return response.body()!!
    }
}