package com.m2f.kotlinmvvm.com.m2f.kotlinmvvm.viewmodel


/**
 * @author Marc Moreno
 * @version 1
 *
 * Interface representing a ViewModel in a MVVM pattern
 */
interface ViewModel {

    fun onPause()

    fun onResume()

    fun onDestroy() {
    }
}