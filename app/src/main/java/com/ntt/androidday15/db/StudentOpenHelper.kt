package com.ntt.androidday15.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ntt.androidday15.Util.Const

class StudentOpenHelper(context: Context) :
    SQLiteOpenHelper(context, "student.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Const.QUERY_CREATE_STUDENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}