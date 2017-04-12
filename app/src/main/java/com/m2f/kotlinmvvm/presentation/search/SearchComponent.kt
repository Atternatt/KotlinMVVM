package com.m2f.kotlinmvvm.presentation.search

import com.m2f.kotlinmvvm.main.di.ApplicationComponent
import dagger.Component
import com.m2f.kotlinmvvm.di.CustomScope

/**
 * @author Marc Moreno
 * @version 1
 */
@CustomScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(SearchModule::class))
interface SearchComponent {

    fun inject(activity: SearchActivity)
}