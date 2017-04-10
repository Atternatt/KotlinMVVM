package com.m2f.kotlinmvvm.main.extensions

import com.jakewharton.retrofit2.adapter.rxjava2.Result
import com.m2f.kotlinmvvm.main.exceptions.RetryableException
import com.m2f.kotlinmvvm.main.exceptions.UnhandledException
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import org.reactivestreams.Publisher
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeoutException


/**
 * @author Marc Moreno
 * @version 1
 */



fun <T> T?.propagate(): Observable<T> {
    return Observable.create { emitter ->
        if(!emitter.isDisposed) {
            this?.let { emitter.onNext(it) }
        }
    }
}

fun <T> T?.propagateFlowable(backpressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST): Flowable<T> {
    return Flowable.create<T>( { emitter ->
        if (!emitter.isCancelled) {
            this?.let { emitter.onNext(it) }
        }
    }, backpressureStrategy)
}

fun <Body> Result<Body>.extractBody(): Body? {
    return if (this.isError) {
        with(this.error()) {
            if (this is IOException) {
                when (this) {
                    is ConnectException -> throw RetryableException()
                    is TimeoutException -> throw RetryableException()
                    else -> throw UnhandledException(this.toString())
                }
            } else {
                android.util.Log.wtf("UNHANDLED_EXCEPTION", this@extractBody.error())
                throw UnhandledException(this.toString())
            }
        }
    } else {
        val response = this.response()
        if (!response.isSuccessful) {
            val code: Int = response.code()
            if (404 == code) {
                return null
            } else {
                throw UnhandledException("http error code: $code")
            }
        } else {
            return response.body()
        }
    }
}

fun <Body> Observable<Result<Body>>.extractResult(): ObservableSource<Body> {
    return this.compose { it.switchMap { it.extractBody().propagate() } }
}

fun <Body> Flowable<Result<Body>>.extractResult(): Publisher<Body> {
    return this.compose { it.switchMap { it.extractBody().propagateFlowable() } }
}