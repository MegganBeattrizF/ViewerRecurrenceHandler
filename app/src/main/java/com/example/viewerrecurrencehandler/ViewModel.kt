package com.example.viewerrecurrencehandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val mutableLiveData = MutableLiveData<Int>()
    val liveData: LiveData<Int> = mutableLiveData

    fun fetchBanner() = mutableLiveData.postValue(Banner().recurrenceTimer)
}