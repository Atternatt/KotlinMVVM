package com.m2f.kotlinmvvm.presentation.search

import android.databinding.ObservableField
import android.graphics.Color
import android.util.Log
import com.m2f.kotlinmvvm.domain.concert.Concert
import com.m2f.kotlinmvvm.domain.concert.SearchConcertsInteractor
import com.m2f.kotlinmvvm.main.extensions.colors
import com.m2f.kotlinmvvm.main.extensions.observe
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

    val concertList: ObservableField<List<Concert>> = ObservableField(listOf())

    var numberOfResults: ObservableField<Int> = ObservableField(0)

    var color = ObservableField<Int>(Color.WHITE)

    private fun updateDensityColor(numberOfConcerts: Int) {
        color.set(colors[Math.min(numberOfConcerts, colors.size -1)])
    }

    init {
        this += concertList.observe().subscribe { numberOfResults.set(it.size) }
    }

    fun initSearchArtifact(searchQueries: Observable<String>) {
        searchConcertsInteractor.execute(searchQueries,
                onNext = { concertList.set(it); updateDensityColor(it.size)},
                onError = { Log.wtf("UNEXPECTED!!", ":( -> ${it.message ?: it.toString()}")},
                onComplete = { Log.d("COMPLETED", "completed!") })
    }

}