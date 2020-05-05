package com.onurcemkarakoc.every10secondscall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val listSize: MutableLiveData<Int> = MutableLiveData()

    val jokeModels: MutableLiveData<ArrayList<JokeModel>> = MutableLiveData()

}