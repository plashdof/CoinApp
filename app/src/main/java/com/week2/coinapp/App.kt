package com.week2.coinapp

import android.app.Application
import android.content.Context
import timber.log.Timber

class App : Application() {


    // merge 테스트용
    init{
        instance=this
    }

    //ㄴㅇㄹㄴㅇㄹㄴㅇ
    companion object{
        //ㄴ ㅇㄹㄴㅇㄹㄴㅇㄹ
        private var instance : App? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }
    // ㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ
    ////////
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}