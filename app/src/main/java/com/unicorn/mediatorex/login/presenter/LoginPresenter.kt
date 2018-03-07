package com.unicorn.mediatorex.login.presenter

import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.login.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginPresenter(private val loginView: LoginView, private val loginService: LoginService) {

    fun getVercode(phoneNo: String) {
        loginView.showLoading("获取验证码中")
        loginService.getVerifyCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            loginView.hideLoading()
                            if (it is HttpException && it.code() == 400) {
                                it.response().errorBody()?.string().let { loginView.showMsg(it!!) }
                            }
                        },
                        onSuccess = {
                            loginView.hideLoading()
                            loginView.showMsg("验证码已发送")
                        }
                )
    }

}