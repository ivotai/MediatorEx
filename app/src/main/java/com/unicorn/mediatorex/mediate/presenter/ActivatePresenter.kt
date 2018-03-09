package com.unicorn.mediatorex.mediate.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.unicorn.mediatorex.logWrapper
import com.unicorn.mediatorex.mediate.model.Label
import com.unicorn.mediatorex.mediate.service.MediateService
import com.unicorn.mediatorex.mediate.view.ActivateActivity
import com.unicorn.mediatorex.mediate.view.ActivateView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_activate.*


class ActivatePresenter(private val view: ActivateView, private val service: MediateService) {

    private var occupations: List<Label> = ArrayList()

    @SuppressLint("CheckResult")
    fun showOccupation() {
        Observable.just(occupations)
                .concatWith(loadOccupation())
                .filter { !it.isEmpty() }
                .take(1)
                .subscribeBy(
                        onError = { view.hideLoading() },
                        onNext = {
                            view
                                    .hideLoading()
                            occupations = it
                            MaterialDialog.Builder(view as Context)

                                    .title("选择职业")
                                    .items(it.map { it.name })
                                    .itemsCallbackSingleChoice(-1, { dialog, _, which, text ->
                                        view as ActivateActivity
                                        view.tvOccupation.text = it[which].name
                                        true
                                    })
                                    .positiveText("确认")
                                    .show()
                        })
    }

    private fun loadOccupation(): Observable<List<Label>> {
        view.showLoading("获取职业中")
        return service.getOccupation()
                .subscribeOn(Schedulers.io())
                .logWrapper("getOccupation")
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun showSkills() {
        loadSkills().subscribeBy(
                onError = { view.hideLoading() },
                onNext = {
                    view
                            .hideLoading()
                    occupations = it
                    MaterialDialog.Builder(view as Context)
                            .title("选择擅长")
                            .items(it.map { it.name })
                            .itemsCallbackMultiChoice(null) { dialog, which, text ->
                                occupations.filter { occupations.indexOf(it) in which }.joinToString(",") { it.name }.let {
                                    view as ActivateActivity
                                    view.tvSkill.text = it
                                }
                                true
                            }
                            .positiveText("确认")
                            .show()
                })
    }

    private fun loadSkills(): Observable<List<Label>> {
        view.showLoading("获取标识中")
        return service.getPublicTag()
                .subscribeOn(Schedulers.io())
                .logWrapper("getPublicTags")
                .observeOn(AndroidSchedulers.mainThread())
    }

}