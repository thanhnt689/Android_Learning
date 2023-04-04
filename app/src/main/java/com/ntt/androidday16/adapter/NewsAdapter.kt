package com.ntt.androidday16.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ntt.androidday16.R
import com.ntt.androidday16.databinding.ItemNewsBinding
import com.ntt.androidday16.model.News

class NewsAdapter(private val news: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.tvTitle.text = news.title
            binding.tvDate.text = news.pubDate
            binding.tvDescription.text = news.description
            Glide.with(binding.root)
                .load(news.thumb)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }
}