package com.unicorn.mediatorex.mediate.service

import com.unicorn.mediatorex.mediate.model.Label
import io.reactivex.Observable
import retrofit2.http.GET

interface MediateService {

    @GET("public/tags")
    fun getPublicTag(): Observable<List<Label>>

    @GET("public/occupations")
    fun getOccupation(): Observable<List<Label>>

    @GET("api/v1/mediate/tag")
    fun getMediateTag(): Observable<Any>

}