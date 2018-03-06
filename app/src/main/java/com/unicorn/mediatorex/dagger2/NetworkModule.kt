package com.unicorn.mediatorex.dagger2

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.unicorn.mediatorex.NullOnEmptyConverterFactory
import com.unicorn.mediatorex.UserInfo
import com.unicorn.mediatorex.service.MediatorService
import com.unicorn.mediatorex.service.UserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor {
                if (UserInfo.loginResponse==null){
                    val request = it.request().newBuilder()
                            .build()
                  return@addInterceptor it.proceed(request)
                }
                val request = it.request().newBuilder()
                        .addHeader("Cookie", "SESSION=${UserInfo.loginResponse!!.jsessionid}")
                        .build()
                it.proceed(request)
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideMediatorService(retrofit: Retrofit): MediatorService = retrofit.create(MediatorService::class.java)

}