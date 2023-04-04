package com.ntt.androidjetpack.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ntt.androidjetpack.model.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student WHERE _id = :id")
    suspend fun deleteStudentById(id: Int)

    @Query("SELECT * FROM student")
    suspend fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student")
    fun getAllStudentWithLiveData(): LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE _id = :id")
    suspend fun getStudentById(id: Int): Student
}