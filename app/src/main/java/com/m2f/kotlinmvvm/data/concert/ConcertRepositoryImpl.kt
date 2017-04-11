package com.m2f.kotlinmvvm.data.concert

import com.m2f.kotlinmvvm.data.concert.datasource.ConcertsDatasource
import com.m2f.kotlinmvvm.domain.concert.Concert
import com.m2f.kotlinmvvm.domain.concert.ConcertRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Marc Moreno
 * @version 1
 */
class ConcertRepositoryImpl
@Inject constructor(vararg val datasources: ConcertsDatasource) : ConcertRepository {

    override fun getAllConcertsFromArtist(artistName: String): Flowable<List<Concert>> {
        return Flowable.merge(datasources.map { it.getConcertsForArtist(artistName) })
                .firstElement()
                .toFlowable()
    }
}