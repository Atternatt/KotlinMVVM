package com.m2f.kotlinmvvm.presentation

import com.m2f.kotlinmvvm.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Marc Moreno
 * @version 1
 */
@Singleton class UIThread
@Inject constructor(): PostExecutionThread {

    override val scheduler: Scheduler by lazy { AndroidSchedulers.mainThread()}
}