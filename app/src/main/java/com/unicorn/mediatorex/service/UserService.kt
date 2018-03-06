package com.unicorn.mediatorex.service

import com.unicorn.mediatorex.LoginResponse
import com.unicorn.mediatorex.RegisterParam
import io.reactivex.Observable
import retrofit2.http.*


interface UserService {

    @GET("register/verifyCode")
    fun getVerifyCode(@Query("phoneNo") phoneNo: String): Observable<String>

    @Headers("Content-Type: application/json")
    @POST("register/mobile")
    fun register(@Body registerParam: RegisterParam): Observable<Any>

    @FormUrlEncoded
    @POST("login/default")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("login/token")
    fun loginByToken(@Field("token") token: String): Observable<LoginResponse>

}