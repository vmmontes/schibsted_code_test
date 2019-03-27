package com.vmmontes.exchange

import com.vmmontes.exchange.model.history.HistoryResponseApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseAppApiService {

    @GET("history?start_at=2018-01-01&end_at=2018-09-01&base=GBP&symbols=EUR")
    fun getHistory(/*@Path("start_date") startDate : String,
              @Path("end_date") endDate : String*/) : Call<HistoryResponseApiModel>
}