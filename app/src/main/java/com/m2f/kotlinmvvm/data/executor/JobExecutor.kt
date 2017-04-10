package com.m2f.kotlinmvvm.data.executor

import java.util.concurrent.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class JobExecutor
@Inject constructor() : Executor {
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        this.threadPoolExecutor = ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(),
                JobThreadFactory())
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}
