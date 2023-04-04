package com.ntt.androidjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ntt.androidjetpack.databinding.ItemPasengerBinding
import com.ntt.androidjetpack.model.Passenger

private val passengerDiff = object : DiffUtil.ItemCallback<Passenger>(){
    override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
        return oldItem == newItem
    }

}

class PassengerAdapter : PagingDataAdapter<Passenger, PassengerAdapter.ViewHolder>(passengerDiff) {
    class ViewHolder(private val binding: ItemPasengerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(passenger: Passenger) {
            binding.passenger = passenger
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPasengerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}