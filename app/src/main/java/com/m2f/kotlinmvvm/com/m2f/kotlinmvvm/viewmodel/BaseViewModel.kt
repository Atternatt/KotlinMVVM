package com.m2f.kotlinmvvm.com.m2f.kotlinmvvm.viewmodel

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Marc Moreno
 * @version 1
 */
abstract class BaseViewModel : ViewModel, BaseObservable() {

    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}