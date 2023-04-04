package com.ntt.androidday18

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ntt.androidday18.databinding.ActivityMusicBinding
import com.ntt.androidday18.media.MediaManager
import com.ntt.androidday18.service.DemoBindService
import com.ntt.androidday18.service.MusicService
import android.media.AudioManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private lateinit var musicService: MusicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startMusicService()
        binding.btnNext.setOnClickListener {
            musicService.nextSong()
        }
        binding.btnPlayPause.setOnClickListener {
            musicService.playPauseSong()
            val manager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            if (manager.isMusicActive) {
                binding.btnPlayPause.text = "Pause"
            } else {
                binding.btnPlayPause.text = "Play"
            }
        }
        binding.btnPrevious.setOnClickListener {
            musicService.previousSong()
            val intent = Intent("com.ntt.thanhnt")
            sendBroadcast(intent)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    private fun startIntentService() {
        val intent = Intent(this, DemoBindService::class.java)
        intent.action = "Download"
        startService(intent)
    }


    private fun startMusicService() {
        val intent = Intent(this, MusicService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
        bindService(intent, connection, BIND_AUTO_CREATE)
    }


    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder: MusicService.MusicBinder = service as MusicService.MusicBinder
            musicService = binder.getMusicService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }
}