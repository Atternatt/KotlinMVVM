package com.m2f.kotlinmvvm.main.extensions

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import java.lang.IllegalArgumentException
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Marc Moreno
 * @version 1
 */

object Delegate {
    fun <Param> bundleParam(activity: Activity, name: String, default: Param) = BundleParam(activity, name, default)

    fun <Param> argument(fragment: Fragment, name: String, default: Param) = Argument(fragment, name, default)

    fun <Param> prefParam(activity: Context, name: String, default: Param) = PrefParam(activity, name, default)

}

class BundleParam<Param>(val activity: Activity, val name: String, val default: Param) : ReadOnlyProperty<Activity, Param> {

    val bundle: Bundle by lazy { activity.intent.extras ?: Bundle() }

    override fun getValue(thisRef: Activity, property: KProperty<*>): Param {
        return findValue()
    }

    @Suppress("UNCHECKED_CAST")
    fun findValue(): Param = with(bundle) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> default as Any
        }

        res as Param
    }

}

class Argument<Param>(val activity: Fragment, val name: String, val default: Param) : ReadOnlyProperty<Fragment, Param> {

    val bundle: Bundle by lazy { activity.arguments ?: Bundle() }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): Param {
        return findValue()
    }

    @Suppress("UNCHECKED_CAST")
    fun findValue(): Param = with(bundle) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> default as Any
        }

        res as Param
    }

}

class PrefParam<Param>(val context: Context, val name: String, val default: Param) : ReadWriteProperty<Any, Param> {

    val sharedPreferences: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun getValue(thisRef: Any, property: KProperty<*>): Param {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Param) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: Param): Param = with(sharedPreferences) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }

        res as Param
    }

    private fun putPreference(name: String, value: Param) = with(sharedPreferences.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }

}