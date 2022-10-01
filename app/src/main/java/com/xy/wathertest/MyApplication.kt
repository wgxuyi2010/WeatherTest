package com.xy.wathertest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = "kIWqOR5yzd1Yor5t"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}