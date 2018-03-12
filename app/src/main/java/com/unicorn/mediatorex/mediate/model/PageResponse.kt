package com.unicorn.mediatorex.mediate.model


data class PageResponse<out T>(
        val content:List<T>,
		val totalElements: Int,
		val totalPages: Int,
		val last: Boolean,
		val size: Int,
		val number: Int,
		val sort: Any,
		val first: Boolean,
		val numberOfElements: Int
)