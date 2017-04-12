package com.m2f.kotlinmvvm.domain.interactor

import com.m2f.kotlinmvvm.domain.executor.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

/**
 * @author Marc Moreno
 * @version 1
 */
abstract class BaseInteractor<Source, Param>
constructor(val postExecutionThread: PostExecutionThread, val threadExecutor: Executor) : Disposable {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun execute(param: Param,
                onNext: (Source) -> Unit = {},
                onError: (Throwable) -> Unit = {},
                onComplete: () -> Unit = {}
    ) {
        addDisposable(buildFlowable(param)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler).subscribe(onNext, onError, onComplete))
    }

    abstract fun buildFlowable(param: Param): Flowable<Source>

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun isDisposed(): Boolean {
        return compositeDisposable.isDisposed
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}