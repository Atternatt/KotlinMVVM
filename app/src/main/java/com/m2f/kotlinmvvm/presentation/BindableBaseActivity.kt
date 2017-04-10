package com.m2f.kotlinmvvm.presentation

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.m2f.kotlinmvvm.main.di.ComponentReflectionInjector
import com.m2f.kotlinmvvm.presentation.viewmodel.ViewModel
import io.folioapp.android.di.Injector

/**
 * @author Marc Moreno
 * @version 1
 */
abstract class BindableBaseActivity<out Component : Any, ViewBinding : ViewDataBinding>: Activity(){

    abstract val component: Component

    lateinit var viewBindig: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentInjector: Injector = ComponentReflectionInjector(component)
        componentInjector.inject(this)

        viewBindig = DataBindingUtil.setContentView(this, layout())

        viewBindig.setVariable(id(), viewModel())

    }

    override fun onPause() {
        super.onPause()
        viewModel().onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel().onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel().onDestroy()
    }

    abstract fun viewModel(): ViewModel

    @LayoutRes
    abstract fun layout(): Int

    /**
     * este id es el id del viewwModel en Datavinders, se accede mediante el objecto [BR]
     */
    abstract fun id(): Int
}