package com.vmmontes.exchange.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryResponseApiModel(
    val base : String,
    @SerializedName("rates")
    @Expose
    val rates : Map<String, CurrencyApiModel>,
    val end_at: String,
    val start_at: String
)