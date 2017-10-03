package com.example.primayudantra.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by primayudantra on 10/3/17.
 */
interface api {
    @GET("3/discover/movie")
    fun movieResponse(@Query("api_key")key: String): Call<MovieResponse>
}