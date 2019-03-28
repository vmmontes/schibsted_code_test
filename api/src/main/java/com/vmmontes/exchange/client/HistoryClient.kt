package com.vmmontes.exchange.client

import com.vmmontes.exchange.model.history.ResultHistoryApiModel

interface HistoryClient {
    fun get(startDate : String, endDate : String) : ResultHistoryApiModel
}