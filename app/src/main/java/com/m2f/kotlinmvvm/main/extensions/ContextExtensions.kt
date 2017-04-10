package com.m2f.kotlinmvvm.main.extensions

import android.app.Activity
import android.app.Application
import com.m2f.kotlinmvvm.main.di.ApplicationComponent
import com.m2f.kotlinmvvm.presentation.MyApplication

/**
 * @author Marc Moreno
 * @version 1
 */

val Application.component: ApplicationComponent
    get() = (this as MyApplication).applicationComponent

val Activity.appComponent: ApplicationComponent
    get() = this.application.component