package com.ntt.androidday18.utils

object Const {
    const val ACTION_NEXT_SONG = "com.ntt.music.NEXT_SONG"
    const val ACTION_PREVIOUS_SONG = "com.ntt.music.PREVIOUS_SONG"
    const val ACTION_PLAY_PAUSE_SONG = "com.ntt.music.PLAY_PAUSE_SONG"
    const val ACTION_STOP_SONG = "com.ntt.music.STOP_SONG"

    const val TB_STUDENT: String = "student"
    const val COL_ID: String = "_id"
    const val COL_NAME: String = "_name"
    const val COL_AGE: String = "_age"
    const val COL_CLASS: String = "_class"

    const val QR_CREATE_TB_STUDENT = """
        CREATE TABLE $TB_STUDENT(
        $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
        $COL_NAME TEXT, 
        $COL_AGE INTEGER, 
        $COL_CLASS TEXT)"""

}