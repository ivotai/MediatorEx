package com.unicorn.mediatorex.mediate.presenter

import android.annotation.SuppressLint
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.mediate.model.*
import com.unicorn.mediatorex.mediate.service.MediateService
import com.unicorn.mediatorex.mediate.view.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_activate.*


class ActivatePresenter(private val view: ActivateView, private val service: MediateService) {

    val activateInfo = ActivateInfo()


    @SuppressLint("CheckResult")
    fun showOccupation() {
        loadOccupation()
                .subscribeBy(
                        onError = { view.hideLoading() },
                        onNext = {
                            view.hideLoading()
                            showLabelPicker(it, (view as AppCompatActivity).supportFragmentManager, object : PickerListener {
                                override fun onPickerConfirm(label: Label) {
                                    activateInfo.occupation = label
                                    view as ActivateActivity
                                        view.tvOccupation.text = label.name
                                }
                            })
                        })
    }


    fun showLabelPicker(labels: List<Label>, fm: FragmentManager, listener: PickerListener) {
        val bf = CommomPickerFragment()
        bf.labels = labels
        bf.listener = listener
        bf.show(fm, "sf")
    }


    fun showRegionPicker(region1s: List<Region1>, fm: FragmentManager, listener: PickerListener) {
        val bf = RegionPickerFragment()
        bf.region1 = region1s
        bf.listener = listener
        bf.show(fm, "sf")
    }


    private fun loadOccupation(): Observable<List<Label>> {
        view.showLoading("获取职业中")
        return service.getPublicOccupations()
                .subscribeOn(Schedulers.io())
                .logWrapper("getPublicOccupations")
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun showSkills() {
        loadSkills().subscribeBy(
                onError = { view.hideLoading() },
                onNext = {

                    activateInfo.tags = listOf(it[0].objectId, it[1].objectId)
                    view.hideLoading()
                    showLabelPicker(it, (view as AppCompatActivity).supportFragmentManager, object : PickerListener {
                        override fun onPickerConfirm(label: Label) {
                            activateInfo.tags = listOf(label.objectId)
                            view as ActivateActivity
                            view.tvSkill.text = label.name
                        }
                    })
                })
    }

    private fun loadSkills(): Observable<List<Label>> {
        view.showLoading("获取标识中")
        return service.getPublicTags()
                .subscribeOn(Schedulers.io())
                .logWrapper("getPublicTags")
                .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    fun loadRegion() {
        view.showLoading("加载区域")

        getRegion().subscribe {
//            val region3 = it[0].region2s[0].region3s[0]
//            activateInfo.committeeRegion = region3
//            activateInfo.resideRegion = region3
            view.hideLoading()
            showRegionPicker(it, (view as AppCompatActivity).supportFragmentManager,object :PickerListener{
                override fun onPickerConfirm(label: Label) {
            activateInfo.committeeRegion = label
            activateInfo.resideRegion = label
                    view as ActivateActivity
                    view.tvRegion.text = label.name
                }
            })
        }
    }

    fun active() {
        service.activeMediator(activateInfo)
                .subscribeOn(Schedulers.io())
                .logWrapper("getPublicTags")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            Logger.d(it)
                        },
                        onError = {
                            Logger.d(it)
                        }
                )
    }

    @SuppressLint("CheckResult")
    fun getRegion(): Observable<List<Region1>> =
            service.getPublicRegions()
                    .subscribeOn(Schedulers.io())
                    .logWrapper("getPublicTags")
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        val region1s = ArrayList<Region1>()
                        for (r1 in it as List<Any>) {
                            val region2s = ArrayList<Region2>()
                            r1 as List<Any>
                            for (r2 in r1[2] as List<Any>) {
                                r2 as List<Any>
                                (r2[2] as List<List<String>>)
                                        .map { Region3(it[0], it[1]) }
                                        .let { region2s.add(Region2(r2[0] as String, r2[1] as String, it)) }
                            }
                            region1s.add(Region1(r1[0] as String, r1[1] as String, region2s))
                        }
                        region1s
                    }

}