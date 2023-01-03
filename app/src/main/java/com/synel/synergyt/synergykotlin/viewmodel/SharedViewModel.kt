package com.synel.synergyt.synergykotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val text = MutableLiveData<String>()
}