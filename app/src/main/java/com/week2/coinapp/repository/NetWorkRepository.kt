package com.week2.coinapp.repository

import com.week2.coinapp.network.Api
import com.week2.coinapp.network.RetrofitInstance

class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

}