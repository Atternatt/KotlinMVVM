package com.m2f.kotlinmvvm.main.di

import android.content.Context
import com.m2f.kotlinmvvm.domain.executor.PostExecutionThread
import com.m2f.kotlinmvvm.main.di.ApplicationModule
import dagger.Component
import io.folioapp.android.di.NetworkModule
import retrofit2.Retrofit
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * @author Marc Moreno
 * @version 1
 */
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
@Singleton
interface ApplicationComponent {

    // exposed to sub-components
    fun context(): Context
    fun postExecutionThread(): PostExecutionThread
    fun threadExecutor(): Executor
    fun retrofit(): Retrofit
}