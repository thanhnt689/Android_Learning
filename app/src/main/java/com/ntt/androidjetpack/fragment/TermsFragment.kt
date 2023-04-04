package com.ntt.androidjetpack.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ntt.androidjetpack.databinding.FragmentTermsBinding

class TermsFragment : Fragment() {
    private lateinit var binding: FragmentTermsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root
    }
}