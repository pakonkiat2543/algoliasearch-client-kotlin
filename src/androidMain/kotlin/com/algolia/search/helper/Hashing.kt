package com.algolia.search.helper

import android.util.Base64
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.InternalSerializationApi
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlinx.serialization.internal.HexConverter

// TODO: Remove deprecated HexConverter object
@OptIn(InternalSerializationApi::class)
internal actual fun String.sha256(key: String): String {
    return Mac.getInstance("HmacSHA256").run {
        val secretKey = SecretKeySpec(this@sha256.toByteArray(), "HmacSHA256")

        init(secretKey)
        val hash = doFinal(key.toByteArray())
        HexConverter.printHexBinary(hash, true)
    }
}

internal actual fun String.encodeBase64(): String {
    return Base64.encodeToString(toByteArray(), Base64.DEFAULT)
}

internal actual fun String.decodeBase64(): String {
    return String(Base64.decode(this, Base64.DEFAULT))
}
