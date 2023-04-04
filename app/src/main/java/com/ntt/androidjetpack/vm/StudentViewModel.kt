package com.ntt.androidjetpack.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntt.androidjetpack.db.StudentDatabase
import com.ntt.androidjetpack.model.Student
import com.ntt.androidjetpack.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(private val app: Application) : ViewModel() {

    private val studentRepository: StudentRepository = StudentRepository(
        StudentDatabase.getInstance(app.applicationContext, viewModelScope).getStudentDao()
    )

    val students = studentRepository.getAllStudentWithLiveData()

    fun insertStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.updateStudent(student)
        }
    }

    fun deleteStudentById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.deleteStudentById(id)
        }
    }

    suspend fun getStudentById(id: Int): Student {
        val student = viewModelScope.async(Dispatchers.IO) {
            studentRepository.getStudentById(id)
        }
        return student.await()
    }
}