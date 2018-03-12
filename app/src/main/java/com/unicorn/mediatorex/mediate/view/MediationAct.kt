package com.unicorn.mediatorex.mediate.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unicorn.mediatorex.R
import com.unicorn.mediatorex.app.dagger2.ComponentsHolder
import com.unicorn.mediatorex.logWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mediation.*

class MediationAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediation)

        viewPager.adapter = MediationPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        val service = ComponentsHolder.appComponent.getMediateService()
        service.getMediateCase(0)
                .subscribeOn(Schedulers.io())
                .logWrapper("getMediateCase")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            it
//                            view.hideLoading()
                        },
                        onNext = {
                            it
//                            view.hideLoading()
//                            UserInfo.loginResponse = it
//                            UserInfo.isLogin = true
//                            ToastUtils.showShort("登录成功")
                        }
                )
    }

}
