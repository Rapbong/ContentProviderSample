package com.example.livedatasample.viewmodel

import android.os.AsyncTask
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val eventData by lazy {MutableLiveData<String>()}

    private val runnable = Runnable { eventData.value = "Main Thread" }

    val transformationData: LiveData<String> = Transformations.map(eventData)
        {text -> "Transformation Map $text"}

    val transformationData2: LiveData<String> = Transformations.switchMap(eventData){
        input -> getText(input)
    }

    fun onClick() {
        val handler = Handler()
        handler.postDelayed(runnable, 1500)

        val asyncTask = BackGroundTask(eventData)
        asyncTask.execute()
    }

    private fun getText(text: String): LiveData<String> {
        val data = MutableLiveData<String>()
        data.value = "Transformation Switch Map $text"

        return data
    }
}

class BackGroundTask(private val eventData: MutableLiveData<String>) : AsyncTask<String, String, Int>() {
    override fun doInBackground(vararg p0: String?): Int {
        Thread.sleep(500)
//        eventData.value = "AsyncTask"
        eventData.postValue("AsyncTask")

        return 0
    }
}