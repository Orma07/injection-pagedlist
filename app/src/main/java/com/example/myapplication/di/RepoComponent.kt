package com.example.myapplication.di

import com.example.myapplication.domain.ReposApi
import com.example.myapplication.views.RepoDataFactory
import com.example.myapplication.views.ReposSearchViewFragment
import com.squareup.picasso.Picasso
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        PageListModule::class,
        OkHttpClientModule::class,
        ReposModule::class,
        ViewModelModule::class,
        ViewModelBindingModule::class
    ]
)
interface RepoComponent {
    fun getReposApi(): ReposApi
    fun getRepoDataFactory(): RepoDataFactory

    //per ignettare nel frag
    fun inject(fragment: ReposSearchViewFragment)
}