package com.unicorn.mediatorex.app.model

import com.unicorn.mediatorex.login.model.LoginResponse


object UserInfo {

    lateinit var loginResponse: LoginResponse

    val jsessionid get() = loginResponse.jsessionid

    val loginToken get() = loginResponse.loginToken

    var isLogin = false

    val username get() = loginResponse.currentUser.username

}