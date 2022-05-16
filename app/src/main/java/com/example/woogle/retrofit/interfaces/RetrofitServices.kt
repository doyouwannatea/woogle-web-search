package com.example.woogle.retrofit.interfaces
import com.example.woogle.models.WebSearchResponce
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @Headers(
        "X-RapidAPI-Host: contextualwebsearch-websearch-v1.p.rapidapi.com",
        "X-RapidAPI-Key: 656624af09msh62ed6a9b860a0f8p16a3bfjsndbdea8d9fdf6"
    )
    @GET("Search/WebSearchAPI")
    fun webSearch(
        @Query("q") q: String,
        @Query("pageNumber") pageNumber: Number,
        @Query("pageSize") pageSize: Number,
        @Query("autoCorrect") autoCorrect: Boolean = true
    ): Call<WebSearchResponce>
}