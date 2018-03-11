package com.unicorn.mediatorex.mediate.model

import com.unicorn.mediatorex.app.model.UserInfo
import java.util.*

data class ActivateInfo(
        val name: String = "张三",
        val phoneNo: String = UserInfo.username,
        val years: Int = 10,
        var occupation: Label = Label("", "2"),
        var resideRegion: Label = Label("", "1"),
        val address: String = "舟山路141号",
        var committeeRegion: Label = Label("", "22"),
        val committee: String = "调委会111",
        var tags: List<String> = ArrayList()
)