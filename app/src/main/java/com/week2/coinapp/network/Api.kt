package com.week2.coinapp.network

import com.week2.coinapp.network.model.CurrentPriceList
import retrofit2.http.GET

interface Api {
    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList
}