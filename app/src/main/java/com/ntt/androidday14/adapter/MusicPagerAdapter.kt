package com.ntt.androidday14.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ntt.androidday14.fragment.AlbumFragment
import com.ntt.androidday14.fragment.ArtistFragment
import com.ntt.androidday14.fragment.SongFragment

class MusicPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SongFragment()
            1 -> AlbumFragment()
            2 -> ArtistFragment()
            else -> throw NotImplementedError("Unknown fragment for position: $position")
        }
    }
}