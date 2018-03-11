package com.unicorn.mediatorex.mediate.wheel

import com.unicorn.mediatorex.mediate.model.Label

interface PickerListener {

    fun onPickerConfirm(label: Label)

}