package com.diegocunha.marvelheroguide.model.repository

import com.diegocunha.marvelheroguide.model.data.Response
import io.reactivex.Observable

interface MarvelRepository {

    fun getHeroes(limit: Int, offset: Int): Observable<Response>
}