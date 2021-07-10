package com.manuelcarvalho.weatherapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.weatherapp.model.Root
import com.manuelcarvalho.weatherapp.model.WeatherApiService

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
                        Log.d(TAG, "RxJava  ${weatherIcon.value}")
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


}

