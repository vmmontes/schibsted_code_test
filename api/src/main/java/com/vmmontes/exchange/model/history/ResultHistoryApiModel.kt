package com.vmmontes.exchange.model.history

data class ResultHistoryApiModel(
    var historyResponseApiModel: HistoryResponseApiModel?,
    var requestIsSucces: Boolean
)