package com.unicorn.mediatorex.mediate.model

data class Case(
        val objectId: String,
        val name: String,
        val caseNumber: String,
        val caseType: String,
        val applicantId: String,
        val applicantPhoneNo: String,
        val applicantAddress: String,
        val mediator: String,
        val mediatorId: String,
        val mediatorPhoneNo: String,
        val mediatorAddress: String,
        val submitTime: Long,
        val status: Int,
        val address: String,
        val situation: String,
        val matter: String,
        val litigants: List<Litigant>,
        val imageList: List<Any>,
        val voiceList: List<Any>
)

data class Litigant(
		val name: String,
		val phoneNo: String
)