package com.ntt.androidjetpack.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ntt.androidjetpack.ui.AlbumFragment
import com.ntt.androidjetpack.ui.SongFragment
import java.lang.IllegalArgumentException

class MusicPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SongFragment()
            1 -> AlbumFragment()
            else -> throw IllegalArgumentException("Unknown fragment")
        }
    }
}