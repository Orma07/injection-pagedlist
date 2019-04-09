package com.example.myapplication.domain


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposApi {

    companion object {
        const val BASE_URL = "https://api.github.com/orgs/"
    }
    @GET("{repoName}/repos")
    fun getRepos(@Path("repoName") repoName :  String,
        @Query("page") page : Int ) : Call<ArrayList<Repository>>
}