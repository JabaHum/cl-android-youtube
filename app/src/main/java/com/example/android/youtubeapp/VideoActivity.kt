package com.example.android.youtubeapp

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    lateinit var url:String
    lateinit var player: SimpleExoPlayer
    private var playbackPosition:Long = 0
    var currentWindow = 0
    lateinit var exoPlayerView: SimpleExoPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        url = intent.getStringExtra("video_url")
        //exoPlayerView = findViewById(R.id.exo_player_)
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

    private fun initializePlayer(){

        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player  = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        exo_player_video_activity.player = player

        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "CLYoutube"))
        val extractorsFactory = DefaultExtractorsFactory()

        val videoSource = ExtractorMediaSource(Uri.parse(url),
                dataSourceFactory, extractorsFactory, null, null)

        player.prepare(videoSource)
        player.seekTo(currentWindow, playbackPosition)

    }

    fun releasePlayer(){
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }


}
