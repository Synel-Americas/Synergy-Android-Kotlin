package com.synel.synergyt.synergykotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicViewModel : ViewModel() {
    val userName = MutableLiveData<String>()
}