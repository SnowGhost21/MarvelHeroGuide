package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.BuildConfig
import com.diegocunha.marvelheroguide.extensions.md5
import com.diegocunha.marvelheroguide.model.data.Response
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class MarvelRetrofitRepository(private val api: MarvelApi) : MarvelRepository {

    companion object {
        private val TIMESTAMP = "ts"
        private val API_KEY = "apikey"
        private val HASH = "hash"
    }


    override fun getHeroes(limit: Int, offset: Int): Single<Response> {
        val parameters = createDefaultParameters()
        parameters["limit"] = limit.toString()
        parameters["offset"] = offset.toString()
        parameters["orderBy"] = "name"

        return api.getHeroes(parameters)
    }

    private fun createDefaultParameters(): LinkedHashMap<String, String> {
        val defaultParameters = LinkedHashMap<String, String>()
        val timeStamp = Date().time.toString()
        defaultParameters[TIMESTAMP] = timeStamp
        defaultParameters[API_KEY] = BuildConfig.API_KEY
        defaultParameters[HASH] = (timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY).md5

        return defaultParameters
    }


}