package com.ntt.androidday16.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntt.androidday16.databinding.ItemCommentBinding
import com.ntt.androidday16.model.Comment

class CommentAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.tvTitle.text = comment.name
            binding.tvDescription.text = comment.body
            binding.tvDate.text = comment.email
            
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}