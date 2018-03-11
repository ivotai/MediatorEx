package com.unicorn.mediatorex.mediate.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.app.dagger2.ComponentsHolder
import com.unicorn.mediatorex.app.model.UserInfo
import com.unicorn.mediatorex.clicks
import com.unicorn.mediatorex.mediate.model.Label
import com.unicorn.mediatorex.mediate.presenter.ActivatePresenter
import com.unicorn.mediatorex.mediate.wheel.PickerListener
import com.unicorn.mediatorex.mediate.wheel.WheelPickerFragment
import kotlinx.android.synthetic.main.activity_activate.*

class ActivateActivity : AppCompatActivity(), ActivateView {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activate)
        supportActionBar?.title = "激活身份"

        tvPhoneNo.text = UserInfo.username

        val presenter = ActivatePresenter(this, ComponentsHolder.appComponent.getMediateService())
        tvOccupation.clicks().subscribe { presenter.showOccupation() }
        tvTag.clicks().subscribe { presenter.showSkills() }
        tvRegion.clicks().subscribe { presenter.loadRegion() }
        btnActive.clicks().subscribe {
            presenter.active()
        }
    }

    override fun showWheelPicker(data: List<Label>, listener: PickerListener) {
        WheelPickerFragment().apply {
            this.data = data
            this.listener = listener
        }.show(supportFragmentManager, "wpf")
    }

    override fun renderOccupation(occupation: String) {
        tvOccupation.text = occupation
    }

    override fun renderTag(tag: String) {
       tvTag.text = tag
    }

    private fun bindEvents() {


    }

    private var dialog: MaterialDialog? = null

    override fun showLoading(title: String) {
        dialog = MaterialDialog.Builder(this)
                .title(title)
                .content("请稍后")
                .progress(true, 0)
                .show()
    }

    override fun hideLoading() {
        if (dialog?.isShowing!!) {
            dialog?.dismiss()
        }
    }

}
