package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.androidjetpack.adapter.StudentAdapter
import com.ntt.androidjetpack.databinding.ActivityStudentRoomBinding
import com.ntt.androidjetpack.model.Student
import com.ntt.androidjetpack.vm.StudentViewModel
import com.ntt.androidjetpack.vm.StudentViewModelFactory

class StudentRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentRoomBinding
    private val viewModel: StudentViewModel by viewModels() {
        StudentViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val name = binding.edtName.text.toString()
            val address = binding.edtAddress.text.toString()
            viewModel.insertStudent(Student(name = name, address = address))

        }
        binding.btnDelete.setOnClickListener {
            val id = binding.edtId.text.toString().toInt()
            viewModel.deleteStudentById(id)
        }
        binding.btnUpdate.setOnClickListener {
            val id = binding.edtId.text.toString().toInt()
            val name = binding.edtName.text.toString()
            val address = binding.edtAddress.text.toString()
            viewModel.updateStudent(Student(id, name, address))
        }
        binding.btnFind.setOnClickListener { }

        val adapter = StudentAdapter()
        viewModel.students.observe(this, {
            adapter.submitList(it)
        })

        binding.rvStudent.adapter = adapter
        binding.rvStudent.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}