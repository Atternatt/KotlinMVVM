package com.m2f.kotlinmvvm.presentation

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.m2f.kotlinmvvm.BuildConfig
import com.m2f.kotlinmvvm.main.di.ApplicationComponent
import com.m2f.kotlinmvvm.main.di.ApplicationModule
import com.m2f.kotlinmvvm.main.di.DaggerApplicationComponent

/**
 * @author Marc Moreno
 * @version 1
 */
class MyApplication : Application(){

    companion object {
        lateinit var appContext: Context
            private set
    }

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        if(BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().apply {
                detectAll()
                penaltyLog()
            }.build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().apply {
                detectAll()
                penaltyLog()
            }.build())
        }

    }

}