package com.manuelcarvalho.weatherapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiService {

    private val BASE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getQuakes(): Single<Root> {
        return api.getQuakes()
    }

    fun getQuakes1(startDate: String, endDate: String): Single<Root> {
        return api.getQuakes1("geojson", startDate, endDate)
    }

}