package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ntt.androidjetpack.databinding.ActivityMainBinding
import com.ntt.androidjetpack.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.number.observe(this, {
            // update UI
            binding.tvNumber.text = it.toString()
        })

        binding.btnUp.setOnClickListener {
            viewModel.increaseNumber()
        }
    }
}