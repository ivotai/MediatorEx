package com.unicorn.mediatorex.login.service

import com.unicorn.mediatorex.login.model.LoginResponse
import com.unicorn.mediatorex.login.model.RegisterInfo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*


interface LoginService {

    @GET("register/verifyCode")
    fun getVerifyCode(@Query("phoneNo") phoneNo: String): Single<Any>

    @Headers("Content-Type: application/json")
    @POST("register/mobile")
    fun register(@Body registerInfo: RegisterInfo): Single<Any>

    @FormUrlEncoded
    @POST("login/default")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("login/token")
    fun loginByToken(@Field("token") token: String): Observable<LoginResponse>

}