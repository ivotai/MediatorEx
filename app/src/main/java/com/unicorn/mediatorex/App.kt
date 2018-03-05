package com.unicorn.mediatorex

import android.app.Application
import cn.jpush.im.android.api.JMessageClient
import com.facebook.stetho.Stetho

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        initJMessage()
        // TODO REMOVE WHEN RELEASE
        Stetho.initializeWithDefaults(this)
    }

    private fun initJMessage(){
        // TODO REMOVE WHEN RELEASE
//        JMessageClient.setDebugMode(true)
        JMessageClient.init(this)
    }

}