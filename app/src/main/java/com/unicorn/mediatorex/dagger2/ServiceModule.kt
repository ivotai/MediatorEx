package com.unicorn.mediatorex.dagger2

import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.service.MediatorService
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
    fun provideMediatorService(retrofit: Retrofit): MediatorService = retrofit.create(MediatorService::class.java)

}