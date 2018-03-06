package com.unicorn.mediatorex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.mediatorex.dagger2.ComponentsHolder
import com.unicorn.mediatorex.service.MediatorService
import com.unicorn.mediatorex.service.UserService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ComponentsHolder.appComponent.inject(this)
        tvLogin.setOnClickListener { login() }
        tvGetTag.setOnClickListener { getTag() }
        tvGetOccupation.setOnClickListener { getOccupation() }
    }

    override fun onBackPressed() {
        getVercode()
    }

    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var mediatorService: MediatorService

    private fun getVercode() {
        userService.getVerifyCode("18930158215")
                .subscribeOn(Schedulers.io())
                .retryWhen {
                    Observable.just(1)
                }
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

    private fun login() {
        userService.login("18930158215", "123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            Log.e("result", it.toString())
                        },
                        onNext = {
                            UserInfo.loginResponse = it
                        }
                )
    }

    private fun relogin() {
        userService.loginByToken(UserInfo.loginResponse!!.loginToken)
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
                            UserInfo.loginResponse = it
                        }
                )
    }

    private fun getTag() {
        mediatorService.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            Log.e("result", it.toString())
                        },
                        onNext = {
                            Log.e("result", it.toString())
                        }
                )
    }

    private fun getOccupation() {
        mediatorService.getOccupations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            Log.e("result", it.toString())
                        },
                        onNext = {
                            Log.e("result", it.toString())
                        }
                )
    }

}
