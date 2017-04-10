package com.m2f.kotlinmvvm.data.concert.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.Result
import com.m2f.kotlinmvvm.BuildConfig
import com.m2f.kotlinmvvm.data.concert.model.ConcertResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Marc Moreno
 * @version 1
 */
interface ConcertsGateway {

    @GET("artists/{artist}/events.json?api_version=2.0&app_id=" + BuildConfig.APPID)
    fun getConcerts(@Path("artist") artistName: String): Flowable<Result<ConcertResponse>>
}