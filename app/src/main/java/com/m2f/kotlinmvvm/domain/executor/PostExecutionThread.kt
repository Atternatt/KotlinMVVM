package com.m2f.kotlinmvvm.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler
}