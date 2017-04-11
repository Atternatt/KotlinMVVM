package com.m2f.kotlinmvvm.presentation.search

import com.m2f.kotlinmvvm.domain.concert.SearchConcertsInteractor
import com.m2f.kotlinmvvm.presentation.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Marc Moreno
 * @version 1
 */
class SearchConcertsViewModel
@Inject constructor(val searchConcertsInteractor: SearchConcertsInteractor): BaseViewModel() {

    override var interactorList: List<Disposable> = listOf(searchConcertsInteractor)

    fun initSearchArtifact(searchQueries: Observable<String>) {
        searchConcertsInteractor.execute(searchQueries, {}, {}, {})
    }

}