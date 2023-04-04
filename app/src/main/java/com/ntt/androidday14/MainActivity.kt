package com.ntt.androidday14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.ntt.androidday14.adapter.MusicPagerAdapter
import com.ntt.androidday14.databinding.ActivityMainBinding
import com.ntt.androidday14.fragment.AlbumFragment
import com.ntt.androidday14.fragment.SongFragment
import com.ntt.androidday14.transformer.CubeOutScalingTransformation

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val titles = arrayOf("Song", "Album", "Artist")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        val adapter = MusicPagerAdapter(this)
        binding.vpMusic.adapter = adapter

        TabLayoutMediator(binding.tabMusic, binding.vpMusic) { tab, position ->
            tab.text = titles[position]
            binding.vpMusic.setCurrentItem(position, true)
        }.attach()

        binding.dotsIndicator.setViewPager2(binding.vpMusic)
        binding.navMusic.setNavigationItemSelectedListener(this)
        binding.vpMusic.setPageTransformer(CubeOutScalingTransformation())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerMusic.isDrawerOpen(GravityCompat.START)) {
                binding.drawerMusic.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerMusic.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerMusic.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
            R.id.menu_song -> {
                binding.vpMusic.currentItem = 0
                true
            }
            R.id.menu_album -> {
                binding.vpMusic.currentItem = 1
                true
            }
            R.id.menu_artist -> {
                binding.vpMusic.currentItem = 2
                true
            }
            else -> true
        }
    }
}