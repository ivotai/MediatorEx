package com.unicorn.mediatorex.dagger2

import com.unicorn.mediatorex.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ConfigModule::class])
interface AppComponent {

    fun inject(o: MainActivity)

}