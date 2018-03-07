package com.unicorn.mediatorex.app.model

import com.unicorn.mediatorex.login.model.LoginResponse

object UserInfo {
    lateinit var loginResponse: LoginResponse
    val jessionId get() = loginResponse.jsessionid
}