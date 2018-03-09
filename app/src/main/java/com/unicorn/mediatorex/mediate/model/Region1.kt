package com.unicorn.mediatorex.mediate.model

import android.support.annotation.Size

class Region1(name: String, @Size(36) objectId: String, val region2s: List<Region2>) : Label(name = name, objectId = objectId)