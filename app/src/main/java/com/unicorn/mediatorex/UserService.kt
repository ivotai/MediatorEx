package com.unicorn.mediatorex

import io.reactivex.Observable
import retrofit2.http.*


interface UserService {

    @GET("med/register/verifyCode")
    fun getVerifyCode(@Query("phoneNo") phoneNo: String): Observable<String>

    @Headers("Content-Type: application/json")
    @POST("med/register/mobile")
    fun register(@Body registerParam: RegisterParam): Observable<Any>

    @FormUrlEncoded
    @POST("med/login/default")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("med/login/token")
    fun loginByToken(@Field("token") token: String): Observable<LoginResponse>

    @GET("med/api/v1/mediate/tag")
    fun getTag(): Observable<Any>



}