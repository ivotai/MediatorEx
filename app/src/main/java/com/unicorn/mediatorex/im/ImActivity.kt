package com.unicorn.mediatorex.im

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unicorn.mediatorex.R


class ImActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_im)

    }

//    private fun real() {
//        JMessageClient.login("ivotai1", "111111", object : BasicCallback() {
//            override fun gotResult(code: Int, msg: String) {
//                Log.e("result", "$code $msg")
//            }
//        })
//        JMessageClient.registerEventReceiver(this)
//    }
//
//    fun onEvent(event: MessageEvent) {
//        Log.e("result", event.toString())
//    }

}
