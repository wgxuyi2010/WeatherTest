package com.xy.wathertest.logic.network

import com.xy.wathertest.MyApplication
import com.xy.wathertest.logic.model.DailyResponse
import com.xy.wathertest.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("v2.5/${MyApplication.TOKEN}/{lnt}.{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>

    @GET("v2.5/${MyApplication.TOKEN}/{lng}.{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>
}