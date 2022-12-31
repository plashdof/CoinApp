package com.week2.coinapp.view.Intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.week2.coinapp.R
import com.week2.coinapp.databinding.FragmentIntro2Binding


class IntroFragment2 : Fragment() {

    private lateinit var navController: NavController
    private var _binding : FragmentIntro2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntro2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.action_introFragment2_to_selectActivity)

//            // or
//            val intent = Intent(requireContext(), SelectActivity::class.java)
//            startActivity(intent)

        }
    }

}