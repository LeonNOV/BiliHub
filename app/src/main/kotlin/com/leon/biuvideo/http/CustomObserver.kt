package com.leon.biuvideo.http

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @Author Leon
 * @Time 2021/11/02
 * @Desc
 */
abstract class CustomObserver<T : Any>: Observer<T> {
    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }
}