package com.unicorn.mediatorex

class Response<out T>(val code: Int, val msg: String, val data: T)