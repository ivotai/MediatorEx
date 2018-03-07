package com.unicorn.mediatorex.app.dagger2.module

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ConfigModule {

    @Singleton
    @Provides
    @Named(value = "baseUrl")
    fun baseUrl() = "https://kjgk.natapp4.cc/med/"

}