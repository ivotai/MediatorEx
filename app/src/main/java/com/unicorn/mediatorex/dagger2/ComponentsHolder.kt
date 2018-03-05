package com.unicorn.mediatorex.dagger2



object ComponentsHolder {

//    private lateinit var app: App

//    fun init(app: App) {
//        ComponentsHolder.app = app
//    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}