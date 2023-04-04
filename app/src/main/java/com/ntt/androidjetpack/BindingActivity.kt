package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ntt.androidjetpack.adapter.StudentAdapter
import com.ntt.androidjetpack.base.BaseActivity
import com.ntt.androidjetpack.databinding.ActivityBindingBinding
import com.ntt.androidjetpack.model.Student
import com.ntt.androidjetpack.vm.BindingViewModel

class BindingActivity : BaseActivity() {

    private lateinit var binding: ActivityBindingBinding
    private val viewModel: BindingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = viewModel
        binding.lifecycleOwner = this

//        binding.btnLoad.setOnClickListener {
//            Glide.with(this)
//                .load(binding.edtLink.text.toString())
//                .into(binding.imgAvatar)
//        }
//        viewModel.link.observe(this, {
//            Log.d("doanpt", it)
//        })

        //        binding.btnLoad.setOnClickListener {
//            Glide.with(this).load(binding.edtLink.text.toString()).into(binding.imgAvatar)
//        }

        val students = arrayListOf<Student>(
            Student(1, "ThanhNT", "Tb"),
            Student(2, "ThanhNT", "Tb"),
            Student(3, "ThanhNT", "Tb"),
            Student(4, "ThanhNT", "Tb"),
            Student(5, "ThanhNT", "Tb"),
        )

        val adapter = StudentAdapter()
        adapter.submitList(students)
        binding.rvData.adapter = adapter
        binding.rvData.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}