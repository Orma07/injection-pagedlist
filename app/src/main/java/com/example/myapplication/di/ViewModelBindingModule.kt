package com.example.myapplication.di

import android.arch.lifecycle.ViewModel
import com.example.myapplication.views.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Bind viewModel to map and provide it as dependency to able it to be injected
 */
@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(mainViewModel: MainViewModel): ViewModel
}