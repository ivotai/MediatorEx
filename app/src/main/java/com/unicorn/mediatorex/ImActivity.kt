package com.unicorn.mediatorex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.MessageEvent
import cn.jpush.im.api.BasicCallback

class ImActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_im)
        real()
    }

    private fun real() {
        JMessageClient.login("ivotai1", "111111", object : BasicCallback() {
            override fun gotResult(code: Int, msg: String) {
                Log.e("result", "$code $msg")
            }
        })
        JMessageClient.registerEventReceiver(this)
    }

    fun onEvent(event: MessageEvent) {
        Log.e("result", event.toString())
    }

}
