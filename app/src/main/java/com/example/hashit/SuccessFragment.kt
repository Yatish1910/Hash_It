package com.example.hashit

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs

import com.example.hashit.databinding.FragmentHomeBinding
import com.example.hashit.databinding.FragmentSuccessBinding
import android.text.ClipboardManager as ClipboardManager


class SuccessFragment : Fragment() {
    //private lateinit var clipboardManager: android.content.ClipboardManager
    private val args: SuccessFragmentArgs by navArgs()
    private lateinit var binding: FragmentSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSuccessBinding.inflate(inflater, container, false)
        binding.hashTextView.text = args.hash


        return binding.root
    }


}