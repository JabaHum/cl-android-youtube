package com.example.android.youtubeapp

import android.app.Application
import com.cloudinary.android.MediaManager

class VideoApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        MediaManager.init(this)
    }

}