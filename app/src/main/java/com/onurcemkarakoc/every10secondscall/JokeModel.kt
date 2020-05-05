package com.onurcemkarakoc.every10secondscall


import com.google.gson.annotations.SerializedName

data class JokeModel(
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("value")
    val value: String


) {
    override fun toString(): String {
        return "JokeModel(iconUrl='$iconUrl', value='$value')"
    }
}