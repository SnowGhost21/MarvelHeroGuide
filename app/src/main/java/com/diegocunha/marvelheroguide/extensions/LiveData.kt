package com.diegocunha.marvelheroguide.extensions

import androidx.lifecycle.*

fun <T> createLiveData(block: MutableLiveData<T>.() -> Unit): LiveData<T> = MutableLiveData<T>().apply(block)
