package com.ntt.androidday15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntt.androidday15.databinding.ActivityWelcomeBinding
import com.ntt.androidday15.db.StudentManager
import com.ntt.androidday15.model.Student

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentManager = StudentManager(this)

        binding.btnAdd.setOnClickListener {
            val name = binding.edtName.text.toString()
            val age = binding.edtAge.text.toString()
            val className = binding.edtClassName.text.toString()
            studentManager.addStudent(
                Student(
                    name = name,
                    age = age.toInt(),
                    className = className
                )
            )
        }
        binding.btnEdit.setOnClickListener {
            val id = binding.edtId.text.toString()
            val name = binding.edtName.text.toString()
            val age = binding.edtAge.text.toString()
            val className = binding.edtClassName.text.toString()
            studentManager.updateStudent(Student(id.toInt(), name, age.toInt(), className))
        }
        binding.btnDelete.setOnClickListener {
            val id = binding.edtId.text.toString()
            studentManager.deleteStudentById(id.toInt())
        }

        val students = studentManager.getAllStudent()
    }
}