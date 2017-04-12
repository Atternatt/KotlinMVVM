package com.m2f.kotlinmvvm.main.extensions

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.Observable.create
import android.databinding.Observable as DataBindingObservable

/**
 * @author Marc Moreno
 * @version 1
 */

private inline fun <reified T : DataBindingObservable, R : Any?> T.observe(
        crossinline block: (T) -> R
): Observable<R> = create { subscriber ->
    object : android.databinding.Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: DataBindingObservable, id: Int) = try {
            subscriber.onNext(block(observable as T))
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }.let {
        subscriber.setCancellable { this.removeOnPropertyChangedCallback(it) }
        this.addOnPropertyChangedCallback(it)
    }
}

fun <T : Any> ObservableField<T>.observe() = observe { it() }

operator fun <T : Any?> ObservableField<T>.invoke(): T = get()