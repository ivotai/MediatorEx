package com.unicorn.mediatorex.app.model

import com.unicorn.mediatorex.login.model.LoginResponse

object UserInfo {

    lateinit var loginResponse: LoginResponse

    val jsessionid get() = loginResponse.jsessionid

}