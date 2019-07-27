package com.example.livedatasample.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val eventData by lazy {MutableLiveData<String>()}

    private val runnable = Runnable { eventData.value = "Callback" }

    val transformationData: LiveData<String> = Transformations.map(eventData)
        {text -> "Transformation Map $text"}

    val transformationData2: LiveData<String> = Transformations.switchMap(eventData){
        input -> getText(input)
    }

    fun onClick() {
        val handler = Handler()
        handler.postDelayed(runnable, 1500)
    }

    private fun getText(text: String): LiveData<String> {
        val data = MutableLiveData<String>()
        data.value = "Transformation Switch Map $text"

        return data
    }
}