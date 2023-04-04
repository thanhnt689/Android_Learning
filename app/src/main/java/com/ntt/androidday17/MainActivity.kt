package com.ntt.androidday17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntt.androidday17.adapter.CommentAdapter
import com.ntt.androidday17.databinding.ActivityMainBinding
import com.ntt.androidday17.model.Comment
import com.ntt.androidday17.network.CommentClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CommentAdapter
    private val comments = arrayListOf<Comment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CommentAdapter(comments)
        binding.rvComment.adapter = adapter
        binding.rvComment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        Log.d("ntt", "Before call")
//        getAllCommentSync()
//        getAllCommentAsync()
//        getAllCommentWithCoroutines()
        getAllCommentWithRx()
        Log.d("ntt", "After call")
    }

    private fun getAllCommentWithRx() {
        CommentClient().getAllCommentWithRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
            comments.addAll(data)
            adapter.notifyDataSetChanged()
        }
        // here is result data

        //        CommentClient().getAllCommentsWithRx()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { list -> Observable.fromIterable(list) }
//            .filter { comment -> comment.id % 2 == 0 }
//            .toList()
//            .subscribe { data ->
//                comments.addAll(data)
//                adapter.notifyDataSetChanged()
//            }

    }

    private fun getAllCommentWithCoroutines() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = CommentClient().getAllCommentWithCoroutines()
            comments.addAll(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun getAllCommentAsync() {
        CommentClient().getAllComment().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        comments.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.d("ntt", "${t.message}")
                t.printStackTrace()
            }
        })
    }

    private fun getAllCommentSync() {
        lifecycleScope.launch(Dispatchers.IO) {
            val response: Response<List<Comment>> = CommentClient().getAllComment().execute()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
                    comments.addAll(it)
                    Log.d("ntt", "All Comment Size : ${comments.size}")

                    withContext(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}