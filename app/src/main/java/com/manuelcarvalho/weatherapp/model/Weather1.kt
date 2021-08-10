package com.manuelcarvalho.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather1(
    val place: String?,
    val maxTemp: Double?,
    val minTemp: Double?,
    val barometer: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}