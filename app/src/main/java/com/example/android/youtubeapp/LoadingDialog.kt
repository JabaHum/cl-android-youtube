package com.example.android.youtubeapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window

class LoadingDialog(context: Context?): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loading_dialog)
        setCancelable(false)
    }

}