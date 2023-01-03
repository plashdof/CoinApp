package com.week2.coinapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.week2.coinapp.dataModel.CurrentPrice
import com.week2.coinapp.dataModel.CurrentPriceResult
import com.week2.coinapp.dataStore.MyDataStore
import com.week2.coinapp.db.entity.InterestCoinEntity
import com.week2.coinapp.repository.DBRepository
import com.week2.coinapp.repository.NetWorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SelectViewModel : ViewModel() {


    private val netWorkRepository = NetWorkRepository()
    private val dbRepository = DBRepository()

    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    // 데이터 변화를 관찰하는 LiveData 이용
    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    private val _saved = MutableLiveData<String>()
    val save : LiveData<String>
        get() = _saved

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


    fun setUpFirstFlag() = viewModelScope.launch{
        MyDataStore().setupFirstData()
    }


    // DB에 데이터 저장
    // https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
    fun saveSelectedCoinList(selectedCoinList: ArrayList<String>) = viewModelScope.launch (Dispatchers.IO) {

        // 1. 전체 코인 데이터를 가져와서 -> OK
        for (coin in currentPriceResultList) {

            Timber.d(coin.toString())

            // 2. 내가 선택한 코인인지 아닌지 구분해서
            // 포함하면 TRUE / 포함하지 않으면 FALSE
            val selected = selectedCoinList.contains(coin.coinName)

            val interestCoinEntity = InterestCoinEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded_24H,
                coin.coinInfo.acc_trade_value_24H,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            // 3. 저장
            interestCoinEntity.let {
                dbRepository.insertInterestCoinData(it)
            }

        }

        // 반복문 끝난뒤, 저장이 끝났음을 나타내는 변수 _saved 의 value 를 done 으로 변경
        // background 스레드에서 하지 말고, D
        withContext(Dispatchers.Main) {
            _saved.value = "done"
        }

    }


}