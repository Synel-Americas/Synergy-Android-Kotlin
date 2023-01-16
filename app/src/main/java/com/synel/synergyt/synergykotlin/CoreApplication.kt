package com.synel.synergyt.synergykotlin

import android.app.Application
import com.synel.synergyt.synergykotlin.utils.HyperlinkedDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CoreApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant( HyperlinkedDebugTree())
    }
}