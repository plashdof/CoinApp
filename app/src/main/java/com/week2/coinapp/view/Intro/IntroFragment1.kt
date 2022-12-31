package com.week2.coinapp.view.Intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.week2.coinapp.R
import com.week2.coinapp.databinding.FragmentIntro1Binding


class IntroFragment1 : Fragment() {

    private lateinit var navController: NavController
    private var _binding : FragmentIntro1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.action_introFragment1_to_introFragment2)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}