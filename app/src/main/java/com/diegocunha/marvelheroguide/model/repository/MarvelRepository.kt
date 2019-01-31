package com.diegocunha.marvelheroguide.model.repository

import com.diegocunha.marvelheroguide.model.data.Response
import com.diegocunha.marvelheroguide.model.data.ResponseComic
import io.reactivex.Single

interface MarvelRepository {

    fun getHeroes(limit: Int, offset: Int): Single<Response>

    fun getHeroById(id: Int): Single<Response>

    fun getComicsByHeroId(id: Int): Single<ResponseComic>
}