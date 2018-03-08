package com.unicorn.mediatorex.app.dagger2

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.unicorn.mediatorex.app.dagger2.module.ConfigModule
import com.unicorn.mediatorex.app.dagger2.module.LoggerModule
import com.unicorn.mediatorex.app.dagger2.module.RetrofitModule
import com.unicorn.mediatorex.app.dagger2.module.ServiceModule
import com.unicorn.mediatorex.login.service.LoginService
import com.unicorn.mediatorex.mediate.service.MediateService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ConfigModule::class, RetrofitModule::class, ServiceModule::class,
    LoggerModule::class
])
interface AppComponent {

    fun getLoginService(): LoginService

    fun getMediateService(): MediateService

    fun getDiskLogAdapter(): DiskLogAdapter

    fun getAndroidLogAdapter(): AndroidLogAdapter

}