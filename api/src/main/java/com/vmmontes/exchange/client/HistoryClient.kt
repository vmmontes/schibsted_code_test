package com.vmmontes.exchange.client

import com.vmmontes.exchange.model.history.HistoryResponseApiModel

interface HistoryClient {
    fun get() : HistoryResponseApiModel
}