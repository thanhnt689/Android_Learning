package com.ntt.androidday18

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntt.androidday18.utils.Const

class ProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        val values = ContentValues()
        values.put(Const.COL_NAME, "ThanhNT")
        values.put(Const.COL_AGE, 22)
        values.put(Const.COL_CLASS, "Land2011E")

        contentResolver.insert(
            Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
            values
        )

        val cursor =
            contentResolver.query(
                Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
                null, null, null, null
            )

//        val cursor =
//            contentResolver.query(
//                Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
//                null, "_id=?", arrayOf("2"), null
//            )

//        val cursor =
//            contentResolver.query(
//                Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
//                arrayOf("_name", "_class"), "_id=?", arrayOf("2"), null
//            )

        if (cursor!!.moveToFirst()) {
            val idIndex = cursor.getColumnIndex("_id")
            val nameIndex = cursor.getColumnIndex("_name")
            val ageIndex = cursor.getColumnIndex("_age")
            val classIndex = cursor.getColumnIndex("_class")
            do {
                val id = cursor.getInt(idIndex)
                val name = cursor.getString(nameIndex)
                val age = cursor.getString(ageIndex)
                val className = cursor.getString(classIndex)
            } while (cursor.moveToNext())
        }

        val values1 = ContentValues()
        values1.put("_name", "thanh")
        values1.put("_age", 6)
        values1.put("_class", "Android")
        contentResolver.update(
            Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
            values1, null, null
        )

        contentResolver.delete(
            Uri.parse("content://com.ntt.androidday18.provider.StudentProvider/student"),
            null, null
        )
    }
}