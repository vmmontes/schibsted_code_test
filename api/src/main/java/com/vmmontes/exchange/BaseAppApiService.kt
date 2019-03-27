package com.vmmontes.exchange

import com.vmmontes.exchange.model.history.HistoryResponseApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseAppApiService {

    @GET("history?base=GBP&symbols=EUR")
    fun getHistory(@Query("start_at") startDate : String,
                   @Query("end_at") endDate : String) : Call<HistoryResponseApiModel>
}