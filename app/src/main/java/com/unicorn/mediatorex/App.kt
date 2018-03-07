package com.unicorn.mediatorex

import android.app.Application
import cn.jpush.im.android.api.JMessageClient
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger



class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initJMessage()
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.addLogAdapter(DiskLogAdapter())
        // TODO REMOVE WHEN RELEASE
        Stetho.initializeWithDefaults(this)
    }

    private fun initJMessage() {
        // TODO REMOVE WHEN RELEASE
//        JMessageClient.setDebugMode(true)
        JMessageClient.init(this)
    }

}