package com.unicorn.mediatorex.login.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.app.dagger2.ComponentsHolder
import com.unicorn.mediatorex.clicks
import com.unicorn.mediatorex.login.model.RegisterInfo
import com.unicorn.mediatorex.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "注册&登录"

        etPhoneNo.setText("13611840424")
        etPassword.setText("123456")

        val presenter = LoginPresenter(this, ComponentsHolder.appComponent.getLoginService())
        btnVerifyCode.clicks().subscribe { presenter.getVerifyCode(etPhoneNo.text.toString()) }
        btnRegister.clicks().subscribe {
            presenter.register(RegisterInfo(
                    phoneNo = etPhoneNo.text.toString(),
                    password = etPassword.text.toString(),
                    verifyCode = etVerifyCode.text.toString()
            ))
        }
        btnLogin.clicks().subscribe {
            presenter.login(
                    username = etPhoneNo.text.toString(),
                    password = etPassword.text.toString()
            )
        }
        btnActivateIdentity.clicks().subscribe { presenter.activateIdentity() }
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
