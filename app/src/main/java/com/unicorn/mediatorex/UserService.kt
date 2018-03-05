package com.unicorn.mediatorex

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*


interface UserService {
    @GET("med/register/verifyCode")
    fun getVerifyCode(@Query("phoneNo") phoneNo: String): Observable<Any>

      @FormUrlEncoded
    @POST("med/register/mobile")
    fun register(
              @Field("phoneNo") phoneNo: String,
              @Field("password") password: String,
              @Field("verifyCode") verifyCode: String
    ): Observable<String>
}