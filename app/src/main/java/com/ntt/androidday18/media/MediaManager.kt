package com.ntt.androidday18.media

import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import com.ntt.androidday18.model.Song

object MediaManager {

    val mediaPlayer: MediaPlayer = MediaPlayer()
    private val songList = arrayListOf<Song>()

    private var currentSong = 0

    private const val MEDIA_IDLE = 0
    private const val MEDIA_PLAYING = 1
    private const val MEDIA_PAUSED = 2
    private const val MEDIA_STOPPED = 3

    private var mediaState = MEDIA_IDLE

    fun nextSong() {
        currentSong++
        if (currentSong > songList.size - 1) {
            currentSong = 0
        }
        playPauseSong(true)
    }

    fun playPauseSong(isNews: Boolean = false) {
        if (mediaState == MEDIA_IDLE || mediaState == MEDIA_STOPPED || isNews) {
            //chạy từ đầu
            mediaPlayer.reset()
            val song = songList[currentSong]
            mediaPlayer.setDataSource(song.path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            //song started
            mediaState = MEDIA_PLAYING
        } else if (mediaState == MEDIA_PLAYING) {
            mediaPlayer.pause()
            mediaState = MEDIA_PAUSED
        } else if (mediaState == MEDIA_PAUSED) {
            mediaPlayer.start()
            mediaState = MEDIA_PLAYING
        }
    }

    fun previousSong() {
        currentSong--
        if (currentSong < 0) {
            currentSong = songList.size - 1
        }
        playPauseSong(true)
    }

    fun mediaStop() {
        mediaPlayer.stop()
    }

    fun setCurrentSong(index: Int) {
        currentSong = index
    }

    fun getAllSongFromStorage(context: Context) {
        val columnsName = arrayOf(
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM,
        )

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, columnsName,
            null,
            null,
            null,
            null,
        )!!
        val indexData = cursor.getColumnIndex(columnsName[0])
        val indexTitle = cursor.getColumnIndex(columnsName[1])
        val indexDisplay = cursor.getColumnIndex(columnsName[2])
        val indexDuration = cursor.getColumnIndex(columnsName[3])
        val indexAlbum = cursor.getColumnIndex(columnsName[4])

        val hasData = cursor.moveToFirst()
        if (hasData) {
            songList.clear()
            while (!cursor.isAfterLast) {
                val data = cursor.getString(indexData)
                val title = cursor.getString(indexTitle)
                val displayName = cursor.getString(indexDisplay)
                val album = cursor.getString(indexAlbum)
                val duration = cursor.getLong(indexDuration)
                songList.add(Song(title, data, displayName, album, duration.toString()))
                cursor.moveToNext()
            }
            Log.d("doanpt", "song size: ${songList.size}")
        }
    }
}