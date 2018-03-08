package com.unicorn.mediatorex

import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.HttpException

fun <T> Single<T>.logWrapper(action: String): Single<T> {
    return this.doOnSubscribe { Logger.d("$action...") }
            .doOnSuccess {
                Logger.d("$action success，value: $it")
            }
            .doOnError {
                val error =
                        if (it is HttpException && it.code() == 400) {
                            it.response().errorBody()?.string().let { JSONObject(it).getString("error") }
                        } else it.toString()
                Logger.d("$action error，value:$error")
                ToastUtils.showShort(error)
            }
}

fun <T> Observable<T>.logWrapper(action: String): Observable<T> {
    return this.doOnSubscribe { Logger.d("$action...") }
            .doAfterNext {
                Logger.d("$action success，value: $it")
            }
            .doOnError {
                val error =
                        if (it is HttpException && it.code() == 400) {
                            it.response().errorBody()?.string().let { JSONObject(it).getString("error") }
                        } else it.toString()
                Logger.d("$action error，value:$error")
                ToastUtils.showShort(error)
            }
}
