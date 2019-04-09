package com.example.myapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Provider

/**
 * rende injectable il vieMwModel
 */
class ViewModelsFactory constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        var creator = creators[modelClass]

        if (creator == null) {
            creators.forEach {
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        }
        val finalCreator = creator
                ?: throw IllegalArgumentException("Unknown model class $modelClass")

        return finalCreator.get() as T
    }
}