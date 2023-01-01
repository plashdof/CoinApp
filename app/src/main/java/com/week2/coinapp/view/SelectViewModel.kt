package com.week2.coinapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.week2.coinapp.dataModel.CurrentPrice
import com.week2.coinapp.dataModel.CurrentPriceResult
import com.week2.coinapp.repository.NetWorkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel : ViewModel() {


    private val netWorkRepository = NetWorkRepository()

    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    // 데이터 변화를 관찰하는 LiveData 이용
    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    fun getCurrentCoinList() = viewModelScope.launch{

        // API 통신으로, 결과값 result 에 저장
        val result = netWorkRepository.getCurrentCoinList()

        // 가공된 데이터 넣을 ArrayList.
        // CurrentPriceResult 데이터 형태로 저장할것임.
        currentPriceResultList = ArrayList()


        for(coin in result.data){

            // API 에서 받아오는 맨 마지막 데이터만 형태가 다름.
            // 예외처리로 처리
            try{

                // API 통신으로 받아온 데이터를 가공.
                // 가공된 데이터 currentPriceResultList 에 저장
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)

                currentPriceResultList.add(currentPriceResult)

            }catch(e : java.lang.Exception){
                
            }

        }

        _currentPriceResult.value = currentPriceResultList

    }


}