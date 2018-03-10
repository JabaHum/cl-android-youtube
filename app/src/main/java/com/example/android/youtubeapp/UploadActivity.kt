package com.example.android.youtubeapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_upload.*

class UploadActivity : AppCompatActivity() {

    lateinit var player: SimpleExoPlayer
    private var playbackPosition: Long = 0
    lateinit var uri: Uri
    lateinit var dialog: LoadingDialog
    var currentWindow = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        uri = Uri.parse(intent.getStringExtra("uri"))
        dialog = LoadingDialog(this@UploadActivity)
        upload_button.setOnClickListener {
            if (edit_text.text.toString().isNotEmpty()) {
                uploadVideo(edit_text.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {

        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        exo_player_upload_activity.player = player

        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "CLYoutube"))
        val extractorsFactory = DefaultExtractorsFactory()

        val videoSource = ExtractorMediaSource(uri,
                dataSourceFactory, extractorsFactory, null, null)

        player.prepare(videoSource)
        player.seekTo(currentWindow, playbackPosition)

    }

    fun releasePlayer() {
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }

    private fun uploadVideo(id:String) {
        MediaManager.get()
                .upload(uri)
                .unsigned("UPLOAD_PRESET")
                .option("resource_type", "video")
                .option("public_id", id)
                .callback(object : UploadCallback {
                    override fun onStart(requestId: String) {
                        dialog.show()
                    }

                    override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {

                    }

                    override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                        dialog.dismiss()
                        Toast.makeText(this@UploadActivity, "Upload successful", Toast.LENGTH_LONG).show()
                        finish()
                    }

                    override fun onError(requestId: String, error: ErrorInfo) {
                        dialog.dismiss()
                        Toast.makeText(this@UploadActivity, "Upload was not successful", Toast.LENGTH_LONG).show()
                    }

                    override fun onReschedule(requestId: String, error: ErrorInfo) {
                        dialog.dismiss()
                        Toast.makeText(this@UploadActivity, "Reschedule", Toast.LENGTH_LONG).show()
                    }
                }).dispatch()

    }

}
