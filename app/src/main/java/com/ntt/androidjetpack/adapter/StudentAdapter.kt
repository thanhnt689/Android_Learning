package com.ntt.androidjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntt.androidjetpack.databinding.ItemStudentBinding
import com.ntt.androidjetpack.model.Student

class StudentAdapter() :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private val students = arrayListOf<Student>()

    fun submitList(temp: List<Student>) {
        students.clear()
        students.addAll(temp)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.s = student
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}