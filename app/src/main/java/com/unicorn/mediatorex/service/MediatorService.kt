package com.unicorn.mediatorex.service

import com.unicorn.mediatorex.model.Label
import io.reactivex.Observable
import retrofit2.http.GET

interface MediatorService {

    @GET("public/tags")
    fun getTags(): Observable<List<Label>>

    @GET("public/occupations")
    fun getOccupations(): Observable<List<Label>>

    @GET("api/v1/mediate/tag")
    fun getMediateTag(): Observable<Any>

}