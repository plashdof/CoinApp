package com.week2.coinapp.view.Intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.week2.coinapp.MainActivity
import com.week2.coinapp.R
import com.week2.coinapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private val viewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkFirstFlag()
        viewModel.first.observe(this, Observer{
            if(it){
                // 처음 접속하는 유저 아님
                // 바로 MainActivity 로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                // 처음 접속하는 유저
                // 인트로화면의 visibility 를 VISIBLE 로 설정
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })
    }
}