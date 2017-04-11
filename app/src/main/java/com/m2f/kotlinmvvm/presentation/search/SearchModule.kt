package com.m2f.kotlinmvvm.presentation.search

import com.m2f.kotlinmvvm.data.concert.ConcertRepositoryImpl
import com.m2f.kotlinmvvm.data.concert.datasource.CloudConcertsDatasource
import com.m2f.kotlinmvvm.data.concert.datasource.ConcertsDatasource
import com.m2f.kotlinmvvm.domain.concert.ConcertRepository
import dagger.Module
import dagger.Provides
import io.folioapp.android.di.CustomScope

/**
 * @author Marc Moreno
 * @version 1
 */
@Module
class SearchModule {

    @Provides
    @CustomScope
    fun provideConcertsRepository(cloudConcertsDatasource: CloudConcertsDatasource): Array<ConcertsDatasource> {
        return arrayOf(cloudConcertsDatasource)
    }

    @Provides
    @CustomScope
    fun providesConcertRepository(repository: ConcertRepositoryImpl): ConcertRepository {
        return repository
    }
}