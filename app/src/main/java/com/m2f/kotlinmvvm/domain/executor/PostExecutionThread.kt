package com.m2f.kotlinmvvm.domain.executor

import io.reactivex.Scheduler

/**
 * @author Marc Moreno
 * @version 1
 */
interface PostExecutionThread {

    val scheduler: Scheduler
}