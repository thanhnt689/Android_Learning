package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.androidjetpack.adapter.TaskAdapter
import com.ntt.androidjetpack.databinding.ActivityPagingBinding
import com.ntt.androidjetpack.vm.TaskViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingBinding
    private val viewModel by viewModels<TaskViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launchWhenCreated {
            viewModel.taskPaging.collectLatest {
                adapter.submitData(it)
            }
        }

    }
}