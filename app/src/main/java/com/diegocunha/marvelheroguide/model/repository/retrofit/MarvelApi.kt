package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.model.data.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    fun getHeroes(@QueryMap parameters: Map<String, String>): Observable<Response>
}