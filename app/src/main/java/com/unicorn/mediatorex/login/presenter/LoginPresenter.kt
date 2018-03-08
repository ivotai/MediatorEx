package com.unicorn.mediatorex.login.presenter

import android.app.Activity
import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.mediatorex.app.model.UserInfo
import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.login.model.RegisterInfo
import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.login.view.LoginView
import com.unicorn.mediatorex.mediate.view.ActivateActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class LoginPresenter(private val view: LoginView, private val service: LoginService) {

    fun getVerifyCode(phoneNo: String) {
        view.showLoading("获取验证码中")
        service.getVerifyCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .logWrapper("getVerifyCode")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            view.hideLoading()
                        },
                        onSuccess = {
                            view.hideLoading()
                            ToastUtils.showShort("验证码已发送")
                        }
                )
    }

    fun register(registerInfo: RegisterInfo) {
        view.showLoading("注册中")
        service.register(registerInfo)
                .subscribeOn(Schedulers.io())
                .logWrapper("register")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            view.hideLoading()
                        },
                        onSuccess = {
                            view.hideLoading()
                            ToastUtils.showShort("注册成功")
                        }
                )
    }

    fun login(username: String, password: String) {
        view.showLoading("登录中")
        service.login(username, password)
                .subscribeOn(Schedulers.io())
                .logWrapper("login")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            view.hideLoading()
                        },
                        onNext = {
                            view.hideLoading()
                            UserInfo.loginResponse = it
                            UserInfo.isLogin = true
                            ToastUtils.showShort("登录成功")
                        }
                )
    }

    fun activateIdentity() {
        if (!UserInfo.isLogin)
            ToastUtils.showShort("尚未登录")
        else
            (view as Activity).apply {
                startActivity(Intent(this, ActivateActivity::class.java))
            }
    }

}