package com.example.myapplication.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

import kotlinx.android.synthetic.main.repos_view.*

import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import com.example.myapplication.di.DaggerRepoComponent
import com.example.myapplication.di.ReposModule
import com.example.myapplication.di.ViewModelsFactory
import javax.inject.Inject

class ReposSearchViewFragment : Fragment() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    lateinit var mAdapter: RepoAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repos_view, container, false)
    }

    override fun onResume() {
        super.onResume()

        DaggerRepoComponent
            .builder()
            .build()
            .inject(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RepoAdapter(context!!)
        recyclerView.adapter = mAdapter

        val viewModel : MainViewModel = ViewModelProviders.of(this, viewModelsFactory).get(MainViewModel::class.java)


        viewModel.articleLiveData?.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }
}