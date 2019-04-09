package com.example.myapplication.di

import android.arch.lifecycle.ViewModel
import com.example.myapplication.views.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelModule {

    @ApplicationScope
    @Provides
    fun providesViewModelsFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelsFactory {
        return ViewModelsFactory(creators)
    }
}