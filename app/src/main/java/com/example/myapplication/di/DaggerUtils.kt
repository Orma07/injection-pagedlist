package com.example.myapplication.di


import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import java.lang.annotation.Retention;



@Qualifier
annotation class ApplicationContext{

}

@Retention(RetentionPolicy.CLASS)
annotation class ApplicationScope