package com.ntt.androidday16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.androidday16.adapter.NewsAdapter
import com.ntt.androidday16.databinding.ActivityMainBinding
import com.ntt.androidday16.model.News
import com.ntt.androidday16.parser.CommentParser
import com.ntt.androidday16.parser.NewsParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val news = arrayListOf<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(news)
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch(Dispatchers.IO) {
            val items = NewsParser.getNews("https://cdn.24h.com.vn/upload/rss/trangchu24h.rss")
            news.addAll(items)
            val comments =
                CommentParser.getAllComment("https://jsonplaceholder.cypress.io/comments")
            Log.d("ntt", "Comment size ${comments.size}")
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
    }
}