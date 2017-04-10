package com.m2f.kotlinmvvm.data.concert.datasource

import com.m2f.kotlinmvvm.domain.concert.Concert
import io.reactivex.Flowable

/**
 * @author Marc Moreno
 * @version 1
 */
interface ConcertsDatasource {

    fun getConcertsForArtist(artistName: String): Flowable<List<Concert>>
}