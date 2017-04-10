package com.m2f.kotlinmvvm.main.extensions

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Marc Moreno
 * @version 1
 */
fun <Param> View.invalidator(initialValue: Param, afterChangeBlock: ((Param) -> Unit)? = {}): ReadWriteProperty<Any?, Param> =
        object : ObservableProperty<Param>(initialValue) {
            override fun afterChange(property: KProperty<*>, oldValue: Param, newValue: Param) {
                afterChangeBlock?.invoke(newValue)
                this@invalidator.invalidate()

            }
        }

fun <Param> View.layoutRequester(initialValue: Param, afterChangeBlock: ((Param) -> Unit)? = {}): ReadWriteProperty<Any?, Param> =
        object : ObservableProperty<Param>(initialValue) {
            override fun afterChange(property: KProperty<*>, oldValue: Param, newValue: Param) {
                afterChangeBlock?.invoke(newValue)
                this@layoutRequester.requestLayout()

            }
        }

fun <Param : Number> View.propertyAnimator(initValue: Param, beforAnimationBlock: ((Param) -> Param)? = null): ReadWriteProperty<Any?, Param> =
        object : ReadWriteProperty<Any?, Param> {

            private var innerValue: Param = initValue
            private val persistedInitialValue = innerValue

            override fun getValue(thisRef: Any?, property: KProperty<*>): Param {
                return innerValue
            }

            @Suppress("UNCHECKED_CAST")
            override fun setValue(thisRef: Any?, property: KProperty<*>, value: Param) {

                val finalValue = beforAnimationBlock?.invoke(value) ?: value

                obtainValueAnimator(persistedInitialValue, finalValue).apply {
                    duration = 1500L
                    interpolator = BounceInterpolator()
                    addUpdateListener { innerValue = it.animatedValue as Param; invalidate() }
                }.start()
            }

            private fun obtainValueAnimator(innerValue: Param, value: Param): ValueAnimator {
                return when (innerValue) {
                    is Float -> ValueAnimator.ofFloat(innerValue, value.toFloat())
                    is Double -> ValueAnimator.ofFloat(innerValue.toFloat(), value.toFloat())
                    is Int -> ValueAnimator.ofInt(innerValue, value.toInt())
                    is Short -> ValueAnimator.ofInt(innerValue.toInt(), value.toInt())
                    is Long -> ValueAnimator.ofInt(innerValue.toInt(), value.toInt())
                    else -> ValueAnimator.ofFloat(innerValue.toFloat(), value.toFloat())
                }
            }

        }
