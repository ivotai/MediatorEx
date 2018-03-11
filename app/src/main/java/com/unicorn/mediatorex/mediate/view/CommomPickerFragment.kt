package com.unicorn.mediatorex.mediate.view

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

/**
 * Created by gurleensethi on 15/01/18.
 */

class CommomPickerFragment : BottomSheetDialogFragment() {


    lateinit var labels: List<Label>
    lateinit var listener: PickerListener

    @SuppressLint("CheckResult")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        //Set the custom view
        val view = LayoutInflater.from(context).inflate(R.layout.picker_one, null)
        dialog.setContentView(view)

        val params = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        val picker = view.findViewById<WheelPicker>(R.id.picker)
        picker.data = labels

        val tvConfirm = view.findViewById<TextView>(R.id.tvConfirm)
        tvConfirm.clicks().subscribe {
            listener.onPickerConfirm(labels[picker.currentItemPosition])
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
