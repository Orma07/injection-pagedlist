package com.example.myapplication.views

import com.example.myapplication.utils.RxViewModel
import android.arch.paging.PagedList
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListBuilder
import com.example.myapplication.domain.Repository
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

import java.util.concurrent.Executors
import javax.inject.Inject


class MainViewModel @Inject constructor(private val dataFactory : RepoDataFactory) : RxViewModel()  {


    private var executor: Executor? = null
    private var _articleLiveData: LiveData<PagedList<Repository>>? = null

    var articleLiveData: LiveData<PagedList<Repository>>? = null
        get() = _articleLiveData


    init {
       updatePageList()
    }

    fun updatePageList(){

        executor = Executors.newFixedThreadPool(5)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(RepoDataSource.PAGE_SIZE)
            .setPageSize(RepoDataSource.PAGE_SIZE*2).build()

        _articleLiveData = LivePagedListBuilder(dataFactory, pagedListConfig)
            .setFetchExecutor(executor as ExecutorService)
            .build()
    }


    /* Factory for creating FeatureViewModel instances */
    class Factory(private val repoDataFactory: RepoDataFactory) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repoDataFactory) as T
        }
    }
}