package com.unicorn.mediatorex.login.presenter

import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.login.model.RegisterInfo
import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.login.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class LoginPresenter(private val loginView: LoginView, private val loginService: LoginService) {

    fun getVerifyCode(phoneNo: String) {
        loginView.showLoading("获取验证码中")
        loginService.getVerifyCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .logWrapper("获取验证码")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            loginView.hideLoading()
//                            it.toast400()
                        },
                        onSuccess = {
                            loginView.hideLoading()
                            loginView.showMsg("验证码已发送")
                        }
                )
    }

    fun register(registerInfo: RegisterInfo) {
        loginView.showLoading("注册中")
        loginService.register(registerInfo)
                .subscribeOn(Schedulers.io())
                .logWrapper("注册")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            loginView.hideLoading()
                        },
                        onSuccess = {
                            loginView.hideLoading()
                            loginView.showMsg("注册成功")
                        }
                )
    }

}