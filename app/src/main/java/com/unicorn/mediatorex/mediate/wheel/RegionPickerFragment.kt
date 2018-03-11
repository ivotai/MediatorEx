package com.unicorn.mediatorex.mediate.wheel

import android.annotation.SuppressLint
import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.aigestudio.wheelpicker.WheelPicker
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.clicks
import com.unicorn.mediatorex.mediate.model.Label
import com.unicorn.mediatorex.mediate.model.Region1

/**
 * Created by gurleensethi on 15/01/18.
 */

class RegionPickerFragment : BottomSheetDialogFragment() {


    lateinit var region1: List<Region1>
    lateinit var listener: PickerListener


    @SuppressLint("CheckResult")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        //Set the custom view
        val view = LayoutInflater.from(context).inflate(R.layout.picker_region, null)
        dialog.setContentView(view)

        val params = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        val picker = view.findViewById<WheelPicker>(R.id.picker)
        picker.data = region1
        picker.selectedItemPosition = 0
        val picker2 = view.findViewById<WheelPicker>(R.id.picker2)
        val picker3 = view.findViewById<WheelPicker>(R.id.picker3)
        picker2.data = region1[0].region2s
        picker3.data = region1[0].region2s[0].region3s


        picker.setOnWheelChangeListener(object :WheelPicker.OnWheelChangeListener{
            override fun onWheelSelected(position: Int) {
                picker2.data = region1[position].region2s
            }

            override fun onWheelScrollStateChanged(state: Int) {
            }

            override fun onWheelScrolled(offset: Int) {
            }
        })
        picker2.setOnWheelChangeListener(object :WheelPicker.OnWheelChangeListener{
            override fun onWheelSelected(position: Int) {
                picker3.data = region1[picker.currentItemPosition].region2s[position].region3s
            }

            override fun onWheelScrollStateChanged(state: Int) {
            }

            override fun onWheelScrolled(offset: Int) {
            }
        })
        val tvConfirm = view.findViewById<TextView>(R.id.tvConfirm)
        tvConfirm.clicks().subscribe {
            listener.onPickerConfirm(picker3.data[picker3.currentItemPosition] as Label)
            dismiss()
        }
        val tvCancel = view.findViewById<TextView>(R.id.tvCancel)
        tvCancel.clicks().subscribe { dismiss() }

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = ""

                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            state = "DRAGGING"
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                            state = "SETTLING"
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            state = "EXPANDED"
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            state = "COLLAPSED"
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            dismiss()
                            state = "HIDDEN"
                        }
                    }

                    Toast.makeText(context, "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show()
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }
}
