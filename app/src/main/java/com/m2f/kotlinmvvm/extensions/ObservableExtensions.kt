package com.m2f.kotlinmvvm.extensions

import com.jakewharton.retrofit2.adapter.rxjava2.Result
import io.reactivex.Observable
import retrofit2.Response

/**
 * @author Marc Moreno
 * @version 1
 */


fun <T> Observable<Result<T>>.extractResult(): Observable<Response<T>> {
    return this.compose { it.map { it.response() } }
}
