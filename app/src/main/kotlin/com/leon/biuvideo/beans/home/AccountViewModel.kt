package com.leon.biuvideo.beans.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author Leon
 * @Time 2022/07/30
 * @Desc
 */
class AccountViewModel : ViewModel() {
    val loginStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}