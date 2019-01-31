package com.diegocunha.marvelheroguide.model.repository

import com.diegocunha.marvelheroguide.model.data.Response
import io.reactivex.Observable
import io.reactivex.Single

interface MarvelRepository {

    fun getHeroes(limit: Int, offset: Int): Single<Response>
}