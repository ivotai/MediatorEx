package com.unicorn.mediatorex.mediate.model

import android.support.annotation.Size

class Region2(name: String, @Size(36) objectId: String, val region3s: List<Region3>) : Label(name = name, objectId = objectId)