package com.unicorn.mediatorex.app.dagger2.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.unicorn.mediatorex.app.NobodyConverterFactory
import com.unicorn.mediatorex.app.model.UserInfo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                val pathSegments = chain.request().url().encodedPathSegments()
                if (pathSegments.contains("login") || pathSegments.contains("register"))
                    chain.proceed(chain.request())
                else
                    chain.request().newBuilder()
                            .addHeader("Cookie", "SESSION=${UserInfo.jsessionid}")
                            .build()
                            .let { chain.proceed(it) }
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(NobodyConverterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}