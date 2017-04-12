package com.m2f.kotlinmvvm.domain.concert

import com.m2f.kotlinmvvm.domain.executor.PostExecutionThread
import com.m2f.kotlinmvvm.domain.interactor.BaseInteractor
import com.m2f.kotlinmvvm.main.extensions.propagateFlowable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.InterruptedIOException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Marc Moreno
 * @version 1
 */
class SearchConcertsInteractor
@Inject constructor(
        postExecutionThread: PostExecutionThread,
        threadExecutor: Executor,
        val concertRepository: ConcertRepository) : BaseInteractor<List<Concert>, Observable<String>>(postExecutionThread, threadExecutor) {

    override fun buildFlowable(param: Observable<String>): Flowable<List<Concert>> {
        return param.toFlowable(BackpressureStrategy.LATEST)
                .filter(String::isNotBlank)
                .switchMap { concertRepository.getAllConcertsFromArtist(it) }
                .onErrorResumeNext(listOf<Concert>().propagateFlowable())
                .retryWhen { throwableFlowable ->
                    Flowable.timer(2, TimeUnit.SECONDS)
                            .concatMap { throwableFlowable }
                            .filter { it is InterruptedIOException }
                }
    }

}