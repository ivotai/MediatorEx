package com.unicorn.mediatorex.dagger2

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.PrettyFormatStrategy
import com.unicorn.mediatorex.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggerModule {

    @Singleton
    @Provides
    fun provideAndroidLogAdapter(): AndroidLogAdapter {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(1)
                .build()
        return object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        }
    }

    @Singleton
    @Provides
    fun provideDiskLogAdapter(): DiskLogAdapter = object : DiskLogAdapter() {
        override fun isLoggable(priority: Int, tag: String?): Boolean {
            return BuildConfig.DEBUG
        }
    }

}