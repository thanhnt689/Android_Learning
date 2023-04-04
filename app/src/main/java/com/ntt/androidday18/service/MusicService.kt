package com.ntt.androidday18.service

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.ntt.androidday18.MusicActivity
import com.ntt.androidday18.R
import com.ntt.androidday18.media.MediaManager
import com.ntt.androidday18.receiver.FirstReceiver
import com.ntt.androidday18.receiver.LocalReceiver
import com.ntt.androidday18.receiver.SecondReceiver
import com.ntt.androidday18.utils.Const

class MusicService : Service() {

    private val binder = MusicBinder()
    private val firstReceiver = FirstReceiver()
    private val secondReceiver = SecondReceiver()
    private val localReceiver = LocalReceiver()
    private val musicReceiver = MusicReceiver()
    private lateinit var notificationView: RemoteViews
    private lateinit var builder: Notification.Builder

    override fun onCreate() {
        super.onCreate()
        val musicFilter = IntentFilter()
        musicFilter.addAction(Const.ACTION_STOP_SONG)
        musicFilter.addAction(Const.ACTION_PLAY_PAUSE_SONG)
        musicFilter.addAction(Const.ACTION_PREVIOUS_SONG)
        musicFilter.addAction(Const.ACTION_NEXT_SONG)
        registerReceiver(musicReceiver, musicFilter)


        val firstFilter = IntentFilter()
        firstFilter.addAction("android.intent.action.SCREEN_ON")
        firstFilter.addAction(Intent.ACTION_SCREEN_ON)
        firstFilter.addAction(Intent.ACTION_SCREEN_OFF)
        firstFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        firstFilter.addAction("com.ddona.deptrai")
        firstFilter.priority = 999
        registerReceiver(firstReceiver, firstFilter)

        val secondFilter = IntentFilter()
        secondFilter.addAction("android.intent.action.SCREEN_ON")
        secondFilter.addAction(Intent.ACTION_SCREEN_ON)
        secondFilter.addAction(Intent.ACTION_SCREEN_OFF)
        secondFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        secondFilter.addAction("com.ddona.deptrai")
        secondFilter.priority = 999
        registerReceiver(secondReceiver, secondFilter)

        val localFilter = IntentFilter()
        localFilter.addAction(Intent.ACTION_SCREEN_ON)
        localFilter.addAction(Intent.ACTION_SCREEN_OFF)
        localFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        localFilter.addAction("com.ddona.deptrai")
        LocalBroadcastManager.getInstance(this).registerReceiver(localReceiver, localFilter)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class MusicBinder : Binder() {

        fun getMusicService(): MusicService {
            return this@MusicService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        MediaManager.playPauseSong()
        runForeground()
        return START_STICKY
    }

    private fun runForeground() {
        notificationView = RemoteViews(packageName, R.layout.layout_notification)

        val nextIntent = Intent(Const.ACTION_NEXT_SONG)
        val nextAction = PendingIntent.getBroadcast(
            this,
            1,
            nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationView.setOnClickPendingIntent(R.id.ibNext, nextAction)

        val previousIntent = Intent(Const.ACTION_PREVIOUS_SONG)
        val previousAction = PendingIntent.getBroadcast(
            this,
            1,
            previousIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationView.setOnClickPendingIntent(R.id.ibPre, previousAction)

        val playPauseIntent = Intent(Const.ACTION_PLAY_PAUSE_SONG)
        val playPauseAction = PendingIntent.getBroadcast(
            this,
            1,
            playPauseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationView.setOnClickPendingIntent(R.id.ibPause, playPauseAction)

        val stopIntent = Intent(Const.ACTION_STOP_SONG)
        val stopAction = PendingIntent.getBroadcast(
            this,
            1,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationView.setOnClickPendingIntent(R.id.ibNotiClose, stopAction)

        val clickIntent = Intent(this, MusicActivity::class.java)
        val clickAction = PendingIntent.getActivity(
            this,
            1,
            clickIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationView.setOnClickPendingIntent(R.id.ll_notification, clickAction)

        builder = Notification.Builder(this)
        builder.setContentText("This is music app")
        builder.setContentTitle("Music")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(notificationView)
        } else {
            builder.setContent(notificationView)
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("123", "123", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channel.id)
        }

        val notification = builder.build()
//        notificationManager.notify(1, notification)
        startForeground(1, notification)
    }

    fun nextSong() {
        MediaManager.nextSong()
    }

    fun previousSong() {
        MediaManager.previousSong()
    }

    fun playPauseSong() {
        MediaManager.playPauseSong()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        unregisterReceiver(firstReceiver)
        Log.d("ntt", "service destroy")
        MediaManager.mediaStop()
        super.onDestroy()
    }

    inner class MusicReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("ntt", "music receiver: ${intent?.action}")
            when (intent?.action) {
                Const.ACTION_NEXT_SONG -> {
                    MediaManager.nextSong()
                }
                Const.ACTION_PREVIOUS_SONG -> {
                    MediaManager.previousSong()
                }
                Const.ACTION_PLAY_PAUSE_SONG -> {
                    if (MediaManager.mediaPlayer.isPlaying) {
                        notificationView.setImageViewResource(R.id.ibPause, R.drawable.ic_pause)
                    } else {
                        notificationView.setImageViewResource(R.id.ibPause, R.drawable.ic_play)
                    }
                    MediaManager.playPauseSong()
                    startForeground(1, builder.build())
                }
                Const.ACTION_STOP_SONG -> {
                    this@MusicService.stopForeground(true)
                }
            }
        }

    }
}