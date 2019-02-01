package com.diegocunha.marvelheroguide

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert


fun <T> assertLiveDataEquals(expected: T?, liveData: LiveData<T>, doAfterSubscribe: (() -> Unit)? = null) {
    var value: T? = null
    liveData.observeForever { value = it }
    doAfterSubscribe?.invoke()
    Assert.assertEquals(expected, value)
}

fun <T> assertLiveDataNull(liveData: LiveData<T>, doAfterSubscribe: (() -> Unit)? = null) {
    var value: T? = null
    liveData.observeForever { value = it }
    doAfterSubscribe?.invoke()
    Assert.assertEquals(null, value)
}