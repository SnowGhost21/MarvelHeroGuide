package com.diegocunha.marvelheroguide.model.fixture

import com.diegocunha.marvelheroguide.extensions.md5
import java.util.*

fun createFixtureDefaultParameters(): LinkedHashMap<String, String> {
    val defaultParameters = LinkedHashMap<String, String>()
    val timeStamp = Date().time.toString()
    defaultParameters["timestamp"] = timeStamp
    defaultParameters["apikey"] = "apikey"
    defaultParameters["hash"] = (timeStamp + "privateKey" + "apikey").md5

    return defaultParameters
}