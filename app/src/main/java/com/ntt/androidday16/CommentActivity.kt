package com.ntt.androidday16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntt.androidday16.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}