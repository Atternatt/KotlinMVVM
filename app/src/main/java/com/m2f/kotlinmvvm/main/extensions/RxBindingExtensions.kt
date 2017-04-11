package com.m2f.kotlinmvvm.main.extensions

import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.Observable

/**
 * @author Marc Moreno
 * @version 1
 */


fun MaterialSearchView.textEvents(): Observable<String> {

    return Observable.create { emitter ->

        val textWatcher : MaterialSearchView.OnQueryTextListener = object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!emitter.isDisposed) {
                    emitter.onNext(newText ?: "")
                }
                return true
            }
        }

        this.setOnQueryTextListener(textWatcher)
    }
}