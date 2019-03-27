package com.vmmontes.exchange.client

import com.vmmontes.exchange.RetrofitService
import com.vmmontes.exchange.model.history.HistoryResponseApiModel

class HistoryClientImp: HistoryClient {

    override fun get(): HistoryResponseApiModel {

        val response = RetrofitService.getService().getHistory().execute()

        return response.body()!!
    }
}