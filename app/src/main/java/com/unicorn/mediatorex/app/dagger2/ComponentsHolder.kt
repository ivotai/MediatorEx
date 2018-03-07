package com.unicorn.mediatorex.app.dagger2


object ComponentsHolder {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}