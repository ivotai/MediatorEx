package com.unicorn.mediatorex.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.orhanobut.logger.Logger
import com.unicorn.mediatorex.dagger2.ComponentsHolder


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        // TODO REMOVE WHEN RELEASE
        Stetho.initializeWithDefaults(this)
//       initJMessage()
        initLogger()
    }

    private fun initJMessage() {
        // TODO REMOVE WHEN RELEASE
//        JMessageClient.setDebugMode(true)
//        JMessageClient.init(this)
    }

    private fun initLogger() {
        Logger.addLogAdapter(ComponentsHolder.appComponent.getAndroidLogAdapter())
        Logger.addLogAdapter(ComponentsHolder.appComponent.getDiskLogAdapter())
    }

}