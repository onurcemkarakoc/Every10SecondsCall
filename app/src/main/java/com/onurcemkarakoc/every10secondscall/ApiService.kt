package com.onurcemkarakoc.every10secondscall

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    fun getJoke():Call<JokeModel>
}