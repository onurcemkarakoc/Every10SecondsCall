package com.onurcemkarakoc.every10secondscall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.MyViewHolder>() {
    private val list = ArrayList<JokeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.iconUrl.text= list[position].iconUrl
        holder.value.text= list[position].value
    }

    fun setList(items: List<JokeModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconUrl : TextView = itemView.findViewById(R.id.icon_url)
        val value : TextView = itemView.findViewById(R.id.value)

    }
}