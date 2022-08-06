package com.leon.biuvideo.http

import io.reactivex.rxjava3.core.Observable

/**
 * @Author Leon
 * @Time 2022/08/06
 * @Desc
 */
data class Rank<T : Any>(val name: String, val observer: Observable<T>, val type: Int)
