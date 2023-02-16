package com.synel.synergyt.synergykotlin.di.component

import android.app.Application
import com.synel.synergyt.synergykotlin.CoreApplication
import com.synel.synergyt.synergykotlin.di.module.AppModule
import com.synel.synergyt.synergykotlin.di.module.DBModule
import com.synel.synergyt.synergykotlin.di.module.NetWorkModule
import com.synel.synergyt.synergykotlin.di.module.ServicesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DBModule::class,
        NetWorkModule::class,
        ServicesModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: CoreApplication)
}