package com.passwordmanager.data

interface KeyProvider {
    val realmKey: ByteArray
    fun removeRealmKey()
}
