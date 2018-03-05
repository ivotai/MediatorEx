package com.unicorn.mediatorex

import io.reactivex.Observable
import retrofit2.http.*


interface UserService {
    @GET("med/register/verifyCode")
    fun getVerifyCode(@Query("phoneNo") phoneNo: String): Observable<Any>


//    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("med/register/mobile")
    fun register(@Body  registerParam: RegisterParam): Observable<String>
}