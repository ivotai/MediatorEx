package com.unicorn.mediatorex.mediate.service

import com.unicorn.mediatorex.mediate.model.ActivateInfo
import com.unicorn.mediatorex.mediate.model.Case
import com.unicorn.mediatorex.mediate.model.Label
import com.unicorn.mediatorex.mediate.model.PageResponse
import io.reactivex.Observable
import retrofit2.http.*

interface MediateService {

    @GET("public/tags")
    fun getPublicTags(): Observable<List<Label>>

    @GET("public/occupations")
    fun getPublicOccupations(): Observable<List<Label>>

    @GET("api/v1/mediate/tag")
    fun getMediateTag(): Observable<Any>

    @GET("public/regions")
    fun getPublicRegions(): Observable<Any>

    @Headers("Content-Type: application/json")
    @POST("api/v1/mediate/mediator/active")
    fun activeMediator(@Body activateInfo: ActivateInfo): Observable<Any>

    @GET("mediate/case")
    fun getMediateCase(@Query("status") status:Int): Observable<PageResponse<Case>>


}