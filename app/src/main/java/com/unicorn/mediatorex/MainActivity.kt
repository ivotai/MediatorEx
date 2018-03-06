package com.unicorn.mediatorex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.mediatorex.dagger2.ComponentsHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ComponentsHolder.appComponent.inject(this)
    }

    override fun onBackPressed() {
        getVercode()
    }

    @Inject
    lateinit var userService: UserService

    private fun getVercode() {
        userService.getVerifyCode("18930158215")
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onError = {
                            // TODO 统一处理
                            if (it is HttpException && it.code() == 400) {
                                it.response().errorBody()?.string().let { ToastUtils.showShort(it) }
                            }
                        },
                        onNext = {
                            //                            Log.e("result", it.toString())
                        }
                )
    }

    private fun register() {
        userService.register(RegisterParam("13611840424", "123456", "158736"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
