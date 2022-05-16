package com.example.woogle.retrofit.common

import com.example.woogle.retrofit.RetrofitClient
import com.example.woogle.retrofit.interfaces.RetrofitServices

object Common {
    private const val BASE_URL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}