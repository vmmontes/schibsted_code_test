package com.vmmontes.api.model.history

data class HistoryResponseApiModel(
    val base : String,
    val rates : ArrayList<DayOfHistoryApiModel>
)