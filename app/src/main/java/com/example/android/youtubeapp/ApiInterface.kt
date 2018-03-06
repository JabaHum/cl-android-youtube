package com.example.android.youtubeapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by idorenyin on 2/27/18.
 */

interface ApiInterface {

    @GET("/videos")
    fun getVideos(): Call<Model>

}