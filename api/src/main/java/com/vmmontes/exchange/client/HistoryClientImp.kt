package com.vmmontes.exchange.client

import android.util.Log
import com.vmmontes.exchange.RetrofitService
import com.vmmontes.exchange.model.history.ResultHistoryApiModel
import java.lang.Exception

class HistoryClientImp: HistoryClient {

    override fun get(startDate : String, endDate : String): ResultHistoryApiModel {

        val requestHistoryApiMode = ResultHistoryApiModel(null, false)
        try {
            val response = RetrofitService.getService().getHistory(startDate, endDate).execute()

            if (response.isSuccessful) {
                requestHistoryApiMode.requestIsSucces = true
                requestHistoryApiMode.historyResponseApiModel = response.body()!!
            }
        } catch (e: Exception) {
            Log.e(HistoryClientImp::javaClass.toString(), e.message.toString())
            return requestHistoryApiMode
        }

        return requestHistoryApiMode
    }
}