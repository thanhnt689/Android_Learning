package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.androidjetpack.adapter.PassengerAdapter
import com.ntt.androidjetpack.adapter.PassengerLoadStateAdapter
import com.ntt.androidjetpack.databinding.ActivityPassengerBinding
import com.ntt.androidjetpack.vm.PassengerViewModel
import kotlinx.coroutines.flow.collectLatest

class PassengerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPassengerBinding
    private val viewModel: PassengerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassengerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPassenger.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = PassengerAdapter()
        binding.rvPassenger.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PassengerLoadStateAdapter { adapter.refresh() },
            footer = PassengerLoadStateAdapter { adapter.retry() }
        )

        lifecycleScope.launchWhenCreated {
            viewModel.passengers.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}