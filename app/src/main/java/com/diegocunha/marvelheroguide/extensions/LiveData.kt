package com.diegocunha.marvelheroguide.extensions

import androidx.lifecycle.*

fun <T> createLiveData(block: MutableLiveData<T>.() -> Unit): LiveData<T> = MutableLiveData<T>().apply(block)

fun <T, R> LiveData<T>.map(mapper: (T?) -> R?): LiveData<R> = Transformations.map(this, mapper)

fun <T, R, S> LiveData<T>.combineWith(other: LiveData<R>, combiner: (T?, R?) -> S?): LiveData<S> {
    val result = MediatorLiveData<S>()
    result.addSource(this) {
        result.postValue(combiner(it, other.value))
    }
    result.addSource(other) {
        result.postValue(combiner(this.value, it))
    }
    return result
}