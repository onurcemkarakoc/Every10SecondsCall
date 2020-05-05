package com.onurcemkarakoc.every10secondscall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val vm: MainViewModel by lazy { MainViewModel() }
    private val adapter: JokesAdapter by lazy { JokesAdapter() }
    private val list: ArrayList<JokeModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jokes_rc.adapter = adapter

        MyService.startService(this)

        MyService.getJokeCallback = {
            list.add(it)
            vm.jokeModels.value = list
        }


        vm.jokeModels.observe(this, Observer {
            adapter.setList(it)
            jokes_rc.scrollToPosition(it.size-1)
            vm.listSize.value = adapter.itemCount
        })


        vm.listSize.observe(this, Observer {
            list_size.text = "List size $it"
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        MyService.stopService(this)
    }
}
