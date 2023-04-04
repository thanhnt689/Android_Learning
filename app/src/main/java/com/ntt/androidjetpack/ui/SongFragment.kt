package com.ntt.androidjetpack.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ntt.androidjetpack.MainActivity
import com.ntt.androidjetpack.databinding.FragmentSongBinding
import com.ntt.androidjetpack.vm.MusicViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SongFragment : Fragment() {
    private lateinit var binding: FragmentSongBinding

    //    private lateinit var viewModel: MusicViewModel
    private val viewModel: MusicViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)
//        viewModel = ViewModelProvider(requireActivity()).get(MusicViewModel::class.java)
        viewModel.number.observe(viewLifecycleOwner, {
            binding.tvNumber.text = it.toString()
        })

        // Avoid memory leak when use observeForever, call removeObserver onDestroy
        viewModel.number.observeForever {
            Log.d("thanhnt", "New Data $it")
        }

//        binding.btnUp.setOnClickListener {
//            viewModel.increaseNumber()
//            viewModel.showToast()
//            viewModel.startActivity()
//        }
//
//        viewModel.needToast.observe(viewLifecycleOwner, {
//            if (it) {
//                Toast.makeText(requireContext(), "New Toast", Toast.LENGTH_SHORT).show()
//            }
//        })

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.nextActivity.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            viewModel.showToastChannel.collect {
                if (it) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        viewModel.number.removeObserver {}
        super.onDestroy()
    }
}