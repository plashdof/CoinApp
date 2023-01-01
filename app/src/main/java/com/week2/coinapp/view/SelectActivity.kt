package com.week2.coinapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.coinapp.App
import com.week2.coinapp.MainActivity
import com.week2.coinapp.R
import com.week2.coinapp.databinding.ActivitySelectBinding
import com.week2.coinapp.view.adapter.SelectRVAdapter

class SelectActivity : AppCompatActivity() {

    private val viewModel : SelectViewModel by viewModels()

    private var _binding : ActivitySelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()

        // 데이터의 변화를 관찰.
        viewModel.currentPriceResult.observe(this, Observer{

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

        })

        // 처음 접속 유저인지를 판별하는 flag 데이터를 true 로 바꾸기
        viewModel.setUpFirstFlag()

        binding.laterTextArea.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}