package com.unicorn.mediatorex

class NetworkResponse<out T>(val code: Int, val msg: String, val data: T? = null)