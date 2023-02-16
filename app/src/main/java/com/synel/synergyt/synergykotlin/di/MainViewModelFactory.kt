package com.synel.synergyt.synergykotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.datastore.PreferencesRepository
import com.synel.synergyt.synergykotlin.model.datastore.SynergyPreferencesRepository
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceRepository
import com.synel.synergyt.synergykotlin.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val database: AppDatabase,
    private val webRepository: WebServiceRepository,
    private val dataRepo: PreferencesRepository

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(database, webRepository, dataRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}