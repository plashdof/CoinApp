package com.week2.coinapp.view.Intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.week2.coinapp.dataStore.MyDataStore
import kotlinx.coroutines.launch

class IntroViewModel : ViewModel() {

    private val _first = MutableLiveData<Boolean>()
    val first : LiveData<Boolean>
        get() = _first

    fun checkFirstFlag() = viewModelScope.launch{
        val getData = MyDataStore().getFirstData()

        _first.value = getData
    }
}