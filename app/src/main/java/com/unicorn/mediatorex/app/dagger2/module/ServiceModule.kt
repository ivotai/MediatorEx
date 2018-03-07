package com.unicorn.mediatorex.app.dagger2.module

import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.mediate.service.MediateService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule{

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Singleton
    @Provides
    fun provideMediatorService(retrofit: Retrofit): MediateService = retrofit.create(MediateService::class.java)

}