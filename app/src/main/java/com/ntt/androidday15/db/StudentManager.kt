package com.ntt.androidday15.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ntt.androidday15.Util.Const
import com.ntt.androidday15.model.Student

class StudentManager(private val context: Context) {
    private val studentHelper = StudentOpenHelper(context)
    private val db = studentHelper.writableDatabase

    fun addStudent(student: Student) {
        val sql =
            "INSERT INTO ${Const.TABLE_STUDENT}(${Const.COL_NAME},${Const.COL_AGE},${Const.COL_CLASS_NAME}) VALUES('${student.name}', ${student.age}, '${student.className}');"

        db.execSQL(sql)
    }

    fun addStudent2(student: Student) {
        val values = ContentValues()
        values.put(Const.COL_NAME, student.name)
        values.put(Const.COL_AGE, student.age)
        values.put(Const.COL_CLASS_NAME, student.className)
        db.insert(Const.TABLE_STUDENT, null, values)
    }

    fun updateStudent(student: Student) {
        val sql =
            "UPDATE ${Const.TABLE_STUDENT} SET ${Const.COL_NAME}='${student.name}', ${Const.COL_AGE}=${student.age}, ${Const.COL_CLASS_NAME}='${student.className}' WHERE ${Const.COL_ID} = ${student.id};"
        db.execSQL(sql)
    }

    fun updateStudent2(student: Student) {
        val values = ContentValues()
        values.put(Const.COL_NAME, student.name)
        values.put(Const.COL_AGE, student.age)
        values.put(Const.COL_CLASS_NAME, student.className)
        db.update(Const.TABLE_STUDENT, values, "${Const.COL_ID}=?", arrayOf(student.id.toString()))
    }

    fun deleteStudentById(id: Int) {
        val sql = "DELETE FROM ${Const.TABLE_STUDENT} WHERE ${Const.COL_ID} = $id;"
        db.execSQL(sql)
    }

    fun deleteStudentById2(id: Int) {
        db.delete(Const.TABLE_STUDENT, "${Const.COL_ID}=?", arrayOf(id.toString()))
    }

    fun getAllStudent(): List<Student> {
        val students = arrayListOf<Student>()
        val sql = "SELECT * FROM ${Const.TABLE_STUDENT}"
        val cursor: Cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(Const.COL_ID)
            val nameIndex = cursor.getColumnIndex(Const.COL_NAME)
            val ageIndex = cursor.getColumnIndex(Const.COL_AGE)
            val classNameIndex = cursor.getColumnIndex(Const.COL_CLASS_NAME)
            do {
                val id = cursor.getInt(idIndex)
                val name = cursor.getString(nameIndex)
                val age = cursor.getInt(ageIndex)
                val className = cursor.getString(classNameIndex)
                students.add(Student(id, name, age, className))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }

    fun getAllStudent2(): List<Student> {
        val students = arrayListOf<Student>()

        val cursor: Cursor = db.query(Const.TABLE_STUDENT, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(Const.COL_ID)
            val nameIndex = cursor.getColumnIndex(Const.COL_NAME)
            val ageIndex = cursor.getColumnIndex(Const.COL_AGE)
            val classNameIndex = cursor.getColumnIndex(Const.COL_CLASS_NAME)
            do {
                val id = cursor.getInt(idIndex)
                val name = cursor.getString(nameIndex)
                val age = cursor.getInt(ageIndex)
                val className = cursor.getString(classNameIndex)
                students.add(Student(id, name, age, className))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }
}