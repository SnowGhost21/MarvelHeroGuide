package com.diegocunha.marvelheroguide.extensions

import java.lang.IllegalStateException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

val String.md5: String
get() {
    return md5(this) ?: throw IllegalStateException("Error to compute md5")
}

private fun md5(`in`: String): String? {
    val result: MessageDigest

    try {
        result = MessageDigest.getInstance("MD5")
        result.update(`in`.toByteArray())
        val a = result.digest()
        val len = a.size
        val sb = StringBuilder(len shl 1)
        for (anA in a) {
            sb.append(Character.forDigit(anA.toInt() and 0xf0 shr 4, 16))
            sb.append(Character.forDigit(anA.toInt() and 0x0f, 16))
        }
        return sb.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return null
}