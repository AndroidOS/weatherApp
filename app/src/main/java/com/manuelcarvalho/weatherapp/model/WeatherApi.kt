package com.manuelcarvalho.weatherapp.model

import com.manuelcarvalho.weatherapp.utils.weatherKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )

//    @GET("query?format=geojson&starttime=2019-12-11&endtime=2019-12-12")
//    fun getQuakes(): Single<Root>

//    @GET("data/2.5/weather?q=London,uk&APPID=${weatherKey}")
//    fun getWeather(): Single<Root>

    @GET("data/2.5/weather?q=London")
    fun getWeather(): Single<Root>

//    @GET("query")
//    fun getWeathers(@Query("format") format: String, @Query("starttime") startTime: String, @Query("endtime") endTime: String): Single<Root>
//

}

