package com.ntt.androidday18.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ntt.androidday18.utils.Const

class StudentHelper(context: Context) : SQLiteOpenHelper(context, "student.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(Const.QR_CREATE_TB_STUDENT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}