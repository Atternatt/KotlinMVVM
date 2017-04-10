package com.m2f.kotlinmvvm.main.di

import io.folioapp.android.di.Injector
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Marc Moreno
 * @version 1
 */
class ComponentReflectionInjector<Component : Any> (private val component: Component) : Injector {


    private val methods: HashMap<Class<*>, Method>?

    init {
        methods = getMethods(component.javaClass)
    }

    override fun inject(target: Any) {

        if (methods == null) {
            throw RuntimeException(
                    String.format("%s has no methods", target.javaClass))
        }

        var targetClass: Class<*>? = target.javaClass
        var method: Method? = methods[targetClass]
        while (method == null && targetClass != null) {
            targetClass = targetClass.superclass
            method = methods[targetClass]
        }

        if (method == null || method.name.contentEquals("equals")) {
            throw RuntimeException(
                    String.format("No %s injecting method exists in %s component", target.javaClass,
                            component.javaClass))
        }

        try {
            method.invoke(component, target)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    companion object {

        private val cache = ConcurrentHashMap<Class<*>, HashMap<Class<*>, Method>>()

        private fun getMethods(componentClass: Class<*>): HashMap<Class<*>, Method>? {
            var methods: HashMap<Class<*>, Method>? = cache[componentClass]
            if (methods == null) {
                synchronized(cache) {
                    methods = cache[componentClass]
                    if (methods == null) {
                        methods = HashMap<Class<*>, Method>()
                        for (method in componentClass.methods) {
                            val params = method.parameterTypes
                            if (params.size == 1) {
                                methods!!.put(params[0], method)
                            }
                        }
                        cache.put(componentClass, methods!!)
                    }
                }
            }

            return methods
        }
    }
}