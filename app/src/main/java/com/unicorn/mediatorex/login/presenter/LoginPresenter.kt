package com.unicorn.mediatorex.login.presenter

import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.login.model.RegisterInfo
import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.login.view.LoginView
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
                            view.showMsg("验证码已发送")
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
                            view.showMsg("注册成功")
                        }
                )
    }

}