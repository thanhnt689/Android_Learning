package com.ntt.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ntt.androidjetpack.adapter.MusicPagerAdapter
import com.ntt.androidjetpack.databinding.ActivityMusicBinding
import com.ntt.androidjetpack.vm.MusicViewModel
import com.ntt.androidjetpack.vm.MusicViewModelFactory

class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private val viewModel by viewModels<MusicViewModel>() {
        MusicViewModelFactory(application, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.number.observe(this, {
            Log.d("thanhnt", "New Data $it")
        })

        val tabs = arrayOf("Song", "Album")
        binding.vpMusic.adapter = MusicPagerAdapter(this)
        TabLayoutMediator(binding.tabMusic, binding.vpMusic) { tab, index ->
            tab.text = tabs[index]
        }.attach()
    }
}