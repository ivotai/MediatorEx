package com.unicorn.mediatorex.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.app.dagger2.ComponentsHolder
import com.unicorn.mediatorex.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val presenter = LoginPresenter(this, ComponentsHolder.appComponent.getLoginService())
        btnVercode.setOnClickListener { presenter.getVercode(etPhoneNo.text.toString()) }
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
        dialog?.dismiss()
    }

    override fun showMsg(msg: String) {
        ToastUtils.showShort(msg)
    }

}
