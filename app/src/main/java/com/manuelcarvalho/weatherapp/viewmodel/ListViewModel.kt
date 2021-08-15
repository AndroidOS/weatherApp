package com.manuelcarvalho.weatherapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.weatherapp.model.Root
import com.manuelcarvalho.weatherapp.model.Weather1
import com.manuelcarvalho.weatherapp.model.WeatherApiService
import com.manuelcarvalho.weatherapp.model.WeatherDatabase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "ListViewModel"

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val weatherService = WeatherApiService()
    private val disposable = CompositeDisposable()

    val weatherEvent = MutableLiveData<Root>()
    val weatherIcon = MutableLiveData<String>()





    private fun fetchFromRemote() {

        disposable.add(
            weatherService.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Root>() {
                    override fun onSuccess(weatherList: Root) {
                        weatherEvent.value = weatherList
                        //createWeatherList(weatherList)
                        weatherIcon.value = weatherList.weather?.get(0)?.icon
                        Log.d(TAG, "Icon RxJava  ${weatherIcon.value}")
                        storeWeatherLocally(weatherList)
                    }

                    override fun onError(e: Throwable) {

                        Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                    }

                })
        )
    }

    fun refresh() {

        fetchFromRemote()
    }



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun createWeatherList(weatherList: Root) {
        //var list = mutableListOf<Quake1>()
        Log.d(TAG,"${weatherList.main?.temp}")
//        for (q in weatherList.main!!) {
//            //list.add(Quake1(q.properties?.place.toString(), q.properties?.mag))
//            Log.d(TAG,"${q}")
//        }
        //return list
    }

    private fun storeWeatherLocally(weatherList: Root) {
        launch {
            val list = weatherList
            val dao = WeatherDatabase(getApplication()).weatherDao()
            dao.deleteAllWeather()

            var weather1 = Weather1("Sydney", 21.0,10.0,1004)
            val result = dao.insert(weather1)
            Log.d(TAG,"uuid number = ${result}")
            var i = 0
//            while (i < list.size) {
//                list[i].uuid = result[i].toInt()
//                ++i
//            }

        }
    }



}

