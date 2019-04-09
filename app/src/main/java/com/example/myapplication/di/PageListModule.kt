package com.example.myapplication.di

import com.example.myapplication.domain.ReposApi
import com.example.myapplication.views.RepoDataFactory
import com.example.myapplication.views.RepoDataSource
import dagger.Module
import dagger.Provides

@Module
class  PageListModule{
    @Provides
    fun getRepoDataFactory(repoDataSource: RepoDataSource) : RepoDataFactory{
        return RepoDataFactory(repoDataSource)
    }

    @Provides
    fun  getRepoDataSource( reposApi: ReposApi) : RepoDataSource{
        return  RepoDataSource(reposApi)
    }
}