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






    private fun fetchFromRemote() {

        disposable.add(
            weatherService.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Root>() {
                    override fun onSuccess(weatherList: Root) {

                        Log.d(TAG, "RxJava  ${weatherList}")
                    }

                    override fun onError(e: Throwable) {

                        Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}

