package com.m2f.kotlinmvvm.main.extensions

import com.jakewharton.retrofit2.adapter.rxjava2.Result
import com.m2f.kotlinmvvm.main.exceptions.NoResultsException
import com.m2f.kotlinmvvm.main.exceptions.RetryableException
import com.m2f.kotlinmvvm.main.exceptions.UnhandledException
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeoutException


/**
 * @author Marc Moreno
 * @version 1
 */


fun <T> T?.propagate(): Observable<T> {
    return Observable.create { emitter ->
        if (!emitter.isDisposed) {
            this?.let { emitter.onNext(it) }
        }
    }
}

fun <T> T?.propagateFlowable(backpressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST): Flowable<T> {
    return Flowable.create<T>({ emitter ->
        if (!emitter.isCancelled) {
            this?.let { emitter.onNext(it) }
        }
    }, backpressureStrategy)
}

private fun <Body> Result<Body>.extractBody(): Body? = if (this.isError) {
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
        if (404 == code || 403 == code) {
            throw NoResultsException()
        } else {
            throw UnhandledException("http error code: $code")
        }
    } else {
        response.body()
    }
}

fun <Body> Observable<Result<Body>>.extractResult(): Observable<Body> {
    return this.compose { it.switchMap { it.extractBody().propagate() } }
}

fun <Body> Flowable<Result<Body>>.extractResult(): Flowable<Body> {
    return this.compose { it.switchMap { it.extractBody().propagateFlowable() } }
}