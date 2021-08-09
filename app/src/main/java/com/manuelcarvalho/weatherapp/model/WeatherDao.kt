package com.manuelcarvalho.weatherapp.model

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface WeatherDao {

    @Insert
    suspend fun insertAll(vararg quakes: Weather1): List<Long>

    @androidx.room.Query("SELECT * FROM Weather1")
    suspend fun getAllQuakes(): List<Weather1>

    @androidx.room.Query("SELECT * FROM Weather1 WHERE uuid = :quakeId")
    suspend fun getQuake(quakeId: Int): Weather1

    @androidx.room.Query("DELETE FROM Weather1")
    suspend fun deleteAllQuakes()
}