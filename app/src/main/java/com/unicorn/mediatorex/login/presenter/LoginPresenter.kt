package com.unicorn.mediatorex.login.presenter

import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.login.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class LoginPresenter(private val loginView: LoginView, private val loginService: LoginService) {

    fun getVercode(phoneNo: String) {
        loginView.showLoading("获取验证码中")
        loginService.getVerifyCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .logWrapper("获取验证码")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            it
                            loginView.hideLoading()

                        },
                        onSuccess = {
                            loginView.hideLoading()
                            loginView.showMsg("验证码已发送")
                        }
                )
    }

}