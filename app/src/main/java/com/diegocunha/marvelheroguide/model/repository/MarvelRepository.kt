package com.diegocunha.marvelheroguide.model.repository

import com.diegocunha.marvelheroguide.model.data.Response
import com.diegocunha.marvelheroguide.model.data.ResponseComic
import com.diegocunha.marvelheroguide.model.data.ResponseCreator

interface MarvelRepository {

    suspend fun getHeroes(limit: Int, offset: Int): retrofit2.Response<Response>

    suspend fun getHeroById(id: Int): retrofit2.Response<Response>

    suspend fun getComicsByHeroId(id: Int): retrofit2.Response<ResponseComic>

    suspend fun getComicById(id: Int): retrofit2.Response<ResponseComic>

    suspend fun getCreatorsByComicId(id: Int): retrofit2.Response<ResponseCreator>
}