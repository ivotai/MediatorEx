package com.unicorn.mediatorex.mediate.view

import com.unicorn.mediatorex.mediate.model.Label
import com.unicorn.mediatorex.mediate.wheel.PickerListener

interface ActivateView :BaseView{

    fun showWheelPicker(data:List<Label>,listener: PickerListener)

    fun renderOccupation(occupation:String)

    fun renderTag(tag:String)

}