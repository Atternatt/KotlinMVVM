package com.m2f.kotlinmvvm.main.extensions

import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.Observable
import io.reactivex.Observable.create

/**
 * @author Marc Moreno
 * @version 1
 */


fun MaterialSearchView.textEvents(): Observable<String> = create { emitter ->

    object : MaterialSearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (!emitter.isDisposed) {
                emitter.onNext(newText ?: "")
            }
            return true
        }
    }.let {
        emitter.setCancellable { setOnQueryTextListener(null) }
        this.setOnQueryTextListener(it)
    }

}