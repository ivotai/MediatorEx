package com.unicorn.mediatorex.dagger2

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.unicorn.mediatorex.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ConfigModule::class, RetrofitModule::class, ServiceModule::class,
    LoggerModule::class
])
interface AppComponent {

    fun inject(o: MainActivity)
    fun getDiskLogAdapter(): DiskLogAdapter
    fun getAndroidLogAdapter(): AndroidLogAdapter

}