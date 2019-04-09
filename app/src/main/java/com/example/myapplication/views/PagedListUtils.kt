package com.example.myapplication.views

import android.arch.paging.PageKeyedDataSource
import com.example.myapplication.domain.Repository
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource

import android.util.Log
import com.example.myapplication.domain.ReposApi
import dagger.Module

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject


class RepoDataFactory @Inject constructor (private val repoDataSource: RepoDataSource) : DataSource.Factory<Int, Repository>() {

    var mutableLiveData: MutableLiveData<RepoDataSource> = MutableLiveData()

    override fun create() : DataSource<Int, Repository> {
        mutableLiveData.postValue(repoDataSource)
        return repoDataSource
    }
}

@Module
class RepoDataSource @Inject constructor (private val repoApi: ReposApi)  : PageKeyedDataSource<Int, Repository>(){

    //TODO: implements states

    companion object {
        const val PAGE_SIZE = 3
        const val TAG = "RepoDataSource"
    }

    /*
   This method is responsible to load the data initially
  * when app screen is launched for the first time.
  * We are fetching the first page data from the api
  * and passing it via the callback method to the UI.
  */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {

        val call =  repoApi.getRepos("square", 1)
        call.enqueue(object : Callback<ArrayList<Repository>> {
            override fun onFailure(call: Call<ArrayList<Repository>>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<ArrayList<Repository>>?,
                response: Response<ArrayList<Repository>>?
            )
            {
                callback.onResult(response?.body()!!.toList(), null, 2)

            }
        })
    }

    /*
   * This method it is responsible for the subsequent call to load the data page wise.
   * This method is executed in the background thread
   * We are fetching the next page data from the api
   * and passing it via the callback method to the UI.
   * The "params.key" variable will have the updated value.
   */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize)
        val call =  repoApi.getRepos("square", params.key)
        call.enqueue(object : Callback<ArrayList<Repository>> {
            override fun onFailure(call: Call<ArrayList<Repository>>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<ArrayList<Repository>>?,
                response: Response<ArrayList<Repository>>?
            )
            {
                val nextKey = (if (params.requestedLoadSize == response?.body()?.count()) null else params.key + 1)?.toLong()
                callback.onResult(response?.body()!!.toList(), nextKey?.toInt())

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {

    }

}

