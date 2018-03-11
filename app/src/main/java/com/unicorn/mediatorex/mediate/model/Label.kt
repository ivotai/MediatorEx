package com.unicorn.mediatorex.mediate.model

import android.support.annotation.Size

open class Label(val name: String, @Size(36) val objectId: String) {

    override fun toString(): String {
        return name
    }

}