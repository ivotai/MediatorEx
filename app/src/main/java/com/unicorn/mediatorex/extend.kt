package com.unicorn.mediatorex

import com.orhanobut.logger.Logger
import io.reactivex.Single
import retrofit2.HttpException


fun Single<*>.logWrapper(action: String): Single<*> {
    return this.doOnSubscribe { Logger.d("${action}中...") }
            .doOnSuccess {
                Logger.d("${action}成功，值: $it")
            }
            .doOnError {
                val error =
                if (it is HttpException && it.code() == 400) {
                    it.response().errorBody()?.string()
                }else it.toString()
                Logger.d("${action}失败，错误信息:$error")
            }
}