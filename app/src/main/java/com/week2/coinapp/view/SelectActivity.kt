package com.week2.coinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel.currentPriceResult.observe(this, Observer{

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

        })
    }
}