package com.unicorn.mediatorex.mediate.wheel

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aigestudio.wheelpicker.WheelPicker
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.clicks
import com.unicorn.mediatorex.mediate.model.Label


class WheelPickerFragment : BottomSheetDialogFragment (){



    lateinit var listener: PickerListener
    lateinit var data:List<Label>

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.picker_one,container)
                val picker = view.findViewById<WheelPicker>(R.id.picker)
//        picker.data = labels
        val tvConfirm = view.findViewById<TextView>(R.id.tvConfirm)
        tvConfirm.clicks().subscribe {

            dismiss()
        }
        val tvCancel = view.findViewById<TextView>(R.id.tvCancel)
        tvCancel.clicks().subscribe { dismiss() }
    return view
    }

    //    @SuppressLint("CheckResult")
//    override fun setupDialog(dialog: Dialog, style: Int) {
//        super.setupDialog(dialog, style)
//        //Set the custom view
//        val view = LayoutInflater.from(context).inflate(R.layout.picker_one, null)
//        dialog.setContentView(view)
//
//        val params = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
//        val behavior = params.behavior
//


//
//
//
//        if (behavior != null && behavior is BottomSheetBehavior<*>) {
//            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                    var state = ""
//
//                    when (newState) {
//                        BottomSheetBehavior.STATE_DRAGGING -> {
//                            state = "DRAGGING"
//                        }
//                        BottomSheetBehavior.STATE_SETTLING -> {
//                            state = "SETTLING"
//                        }
//                        BottomSheetBehavior.STATE_EXPANDED -> {
//                            state = "EXPANDED"
//                        }
//                        BottomSheetBehavior.STATE_COLLAPSED -> {
//                            state = "COLLAPSED"
//                        }
//                        BottomSheetBehavior.STATE_HIDDEN -> {
//                            dismiss()
//                            state = "HIDDEN"
//                        }
//                    }
//
//                    Toast.makeText(context, "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//            })
//        }
    }
