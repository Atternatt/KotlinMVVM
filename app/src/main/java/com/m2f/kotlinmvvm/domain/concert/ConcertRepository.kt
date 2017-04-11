package com.m2f.kotlinmvvm.domain.concert

import io.reactivex.Flowable

/**
 * @author Marc Moreno
 * @version 1
 */
interface ConcertRepository {

    fun getAllConcertsFromArtist(artistName: String) : Flowable<List<Concert>>
}