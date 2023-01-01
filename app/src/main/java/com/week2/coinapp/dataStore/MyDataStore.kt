package com.week2.coinapp.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.week2.coinapp.App

class MyDataStore {

    private val context = App.context()

    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    }

    private val mDataStore : DataStore<Preferences> = context.dataStore

    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")

    // 메인 Activity 로 갈때, TRUE 로 변경

    // 처음 접속 유저 아니면 -> TRUE
    // 처음 접속하는 유저이면 -> FALSE
    
    suspend fun setupFirstData(){
        mDataStore.edit{preferences->
            preferences[FIRST_FLAG] = true
        }
    }

    suspend fun getFirstData() : Boolean{
        var currentValue = false

        mDataStore.edit{preferences->
            currentValue = preferences[FIRST_FLAG] ?: false
        }

        return currentValue
    }

}