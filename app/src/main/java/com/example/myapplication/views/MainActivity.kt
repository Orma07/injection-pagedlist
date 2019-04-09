package com.example.myapplication.views


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.utils.NavigationUtils


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUtils.replaceFragment(supportFragmentManager,
            R.id.frameLayout,
            ReposSearchViewFragment())
    }
}
