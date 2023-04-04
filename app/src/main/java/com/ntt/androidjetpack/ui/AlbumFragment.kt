package com.ntt.androidjetpack.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ntt.androidjetpack.databinding.FragmentAlbumBinding
import com.ntt.androidjetpack.vm.MusicViewModel

class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding

    //    private lateinit var viewModel: MusicViewModel
//    private val viewModel by viewModels<MusicViewModel>()
    private val viewModel by activityViewModels<MusicViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)
//        viewModel = ViewModelProvider(requireActivity()).get(MusicViewModel::class.java)
        viewModel.number.observe(viewLifecycleOwner, {
            binding.tvNumber.text = it.toString()
        })
        binding.btnUp.setOnClickListener {
            viewModel.increaseNumber()
        }
        return binding.root
    }
}