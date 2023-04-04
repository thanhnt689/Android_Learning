package com.ntt.androidday15.Util

object Const {
    const val TABLE_STUDENT = "tb_student"
    const val COL_ID = "col_id"
    const val COL_NAME = "col_name"
    const val COL_AGE = "col_age"
    const val COL_CLASS_NAME = "col_class_name"

    const val QUERY_CREATE_STUDENT_TABLE =
        "CREATE TABLE $TABLE_STUDENT($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME TEXT, $COL_AGE INT, $COL_CLASS_NAME TEXT)"

}