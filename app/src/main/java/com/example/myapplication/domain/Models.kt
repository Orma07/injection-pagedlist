package com.example.myapplication.domain

class Repository {
    var owner: Owner? = null
    var name : String? = null
}

class Owner{
    var avatar_url : String? = null
    var login : String? = null
}