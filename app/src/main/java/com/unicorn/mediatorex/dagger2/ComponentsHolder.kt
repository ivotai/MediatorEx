package com.unicorn.mediatorex.dagger2


object ComponentsHolder {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}