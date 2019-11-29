package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.model.data.Response
import com.diegocunha.marvelheroguide.model.data.ResponseComic
import com.diegocunha.marvelheroguide.model.data.ResponseCreator
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getHeroes(@QueryMap parameters: Map<String, String>): retrofit2.Response<Response>

    @GET("characters/{id}")
    suspend fun getHeroById(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): retrofit2.Response<Response>

    @GET("characters/{id}/comics")
    suspend fun getComicsByHeroId(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): retrofit2.Response<ResponseComic>

    @GET("comics/{id}")
    suspend fun getComicById(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): retrofit2.Response<ResponseComic>

    @GET("comics/{id}/creators")
    suspend fun getCreatorsByComicId(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): retrofit2.Response<ResponseCreator>
}