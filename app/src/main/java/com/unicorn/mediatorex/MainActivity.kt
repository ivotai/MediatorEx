package com.unicorn.mediatorex

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {
    private lateinit var userService: UserService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSharedPreferences("name", Context.MODE_PRIVATE).edit().putBoolean("key",true).apply()

        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        val baseUrl = "https://kjgk.natapp4.cc/"
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        userService = retrofit.create(UserService::class.java)
//        userService.getVerifyCode("13611840424")
//                .subscribeOn(Schedulers.io())
//                .subscribeBy(
//                        onComplete = {
//                            Log.e("result", "complete")
//                        },
//                        onError = {
//                            Log.e("result", it.toString())
//                        },
//                        onNext = {
//                            ""
//                            Log.e("result", it.toString())
//                        }
//                )


    }

    override fun onBackPressed() {
        register()
    }

    private fun register() {
        userService.register("13611840424", "123456", "158736")
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            Log.e("result", "complete")
                        },
                        onError = {
                            Log.e("result", it.toString())
                        },
                        onNext = {
                            Log.e("result", it.toString())
                        }
                )
    }
}
