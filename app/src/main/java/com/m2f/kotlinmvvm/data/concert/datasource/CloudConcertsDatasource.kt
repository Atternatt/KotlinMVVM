package com.m2f.kotlinmvvm.data.concert.datasource

import com.m2f.kotlinmvvm.domain.concert.Concert
import io.reactivex.Flowable
import retrofit2.Retrofit

/**
 * @author Marc Moreno
 * @version 1
 */
class CloudConcertsDatasource(retrofit:Retrofit): ConcertsDatasource {

    override fun getConcertsForArtist(artistName: String): Flowable<List<Concert>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}