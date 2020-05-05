package com.onurcemkarakoc.every10secondscall

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository {

    private val apiService: ApiService by lazy { ApiClient.getRetrofit() }
    var jokeModelCallback: ((JokeModel) -> Unit)? = null

    fun getJoke() {
        apiService.getJoke().enqueue(object : Callback<JokeModel> {
            override fun onFailure(call: Call<JokeModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<JokeModel>, response: Response<JokeModel>) {
               jokeModelCallback?.invoke(response.body()!!)
            }

        })
    }
}