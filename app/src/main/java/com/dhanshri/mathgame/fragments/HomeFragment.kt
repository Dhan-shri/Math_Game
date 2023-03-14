package com.dhanshri.mathgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.dhanshri.mathgame.R
import com.dhanshri.mathgame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.btnAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.btnSub.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_subFragment)
        }
        binding.btnMulti.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_multiFragment)
        }
        binding.btnDiv.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_divFragment)
        }

        return binding.root

    }

}
