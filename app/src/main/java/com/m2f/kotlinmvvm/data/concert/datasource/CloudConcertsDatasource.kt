package com.m2f.kotlinmvvm.data.concert.datasource

import com.m2f.kotlinmvvm.data.concert.toBO
import com.m2f.kotlinmvvm.domain.concert.Concert
import com.m2f.kotlinmvvm.main.extensions.extractResult
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author Marc Moreno
 * @version 1
 */
class CloudConcertsDatasource
@Inject constructor(retrofit:Retrofit): ConcertsDatasource {

    val gateway = retrofit.create(ConcertsGateway::class.java)


    override fun getConcertsForArtist(artistName: String): Flowable<List<Concert>> {
        return gateway.getConcerts(artistName)
                .filter { it.isError.not() && it.response().isSuccessful }
                .extractResult()
                .map { it.map { it.toBO() } }
    }
}