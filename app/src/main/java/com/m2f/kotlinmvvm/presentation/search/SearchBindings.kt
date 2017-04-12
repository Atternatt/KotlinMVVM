package com.m2f.kotlinmvvm.presentation.search

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.m2f.kotlinmvvm.domain.concert.Concert
import com.m2f.kotlinmvvm.main.extensions.textEvents
import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Marc Moreno
 * @version 1
 */

@BindingAdapter("bind:search")
fun bindSearchViewToViewModel(searchView: MaterialSearchView,
                              searchConcertsViewModel: SearchConcertsViewModel) {

    val observable = Observable.defer<String> {
        searchView.textEvents()
                .debounce(350L, TimeUnit.MILLISECONDS)
                .filter(String::isNotBlank)
                .map(String::trim)
                .distinctUntilChanged()
    }

    searchConcertsViewModel.initSearchArtifact(observable)
}


@BindingAdapter("bind:concertList")
fun bindListToRecyclerView(recyclerView: RecyclerView, list: List<Concert>?) {

    if (list != null) {
        if (recyclerView.adapter == null) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            val adapter = ConcertListAdapter(list)
            recyclerView.adapter = adapter

        } else {
            (recyclerView.adapter as ConcertListAdapter) += list
        }
    }

}
