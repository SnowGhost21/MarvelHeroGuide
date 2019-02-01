package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.model.data.Response
import com.diegocunha.marvelheroguide.model.data.ResponseComic
import com.diegocunha.marvelheroguide.model.data.ResponseCreator
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    fun getHeroes(@QueryMap parameters: Map<String, String>): Single<Response>

    @GET("characters/{id}")
    fun getHeroById(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): Single<Response>

    @GET("characters/{id}/comics")
    fun getComicsByHeroId(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): Single<ResponseComic>

    @GET("comics/{id}")
    fun getComicById(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): Single<ResponseComic>

    @GET("comics/{id}/creators")
    fun getCreatorsByComicId(@Path("id") id: Int, @QueryMap parameters: Map<String, String>): Single<ResponseCreator>
}