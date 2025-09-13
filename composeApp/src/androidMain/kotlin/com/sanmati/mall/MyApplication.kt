package com.sanmati.mall

import android.app.Application
import com.sanmati.mall.commonUtils.SharedPreference

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPreference.init(this)
    }
}