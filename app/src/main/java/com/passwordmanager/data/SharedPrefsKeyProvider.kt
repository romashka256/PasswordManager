package com.passwordmanager.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import timber.log.Timber
import java.security.SecureRandom
import java.util.*

class SharedPrefsKeyProvider(internal var mAppSharedPrefs: SharedPreferences) : KeyProvider {

    override val realmKey: ByteArray
        get() {
            val key: ByteArray
            val savedKey = getStringFromPrefs(REALM_KEY)
            if (savedKey.isEmpty()) {
                key = generateKey()
                val keyString = encodeToString(key)
                saveStringToPrefs(keyString)
            } else {
                key = decodeFromString(savedKey)
            }
            return key
        }

    override fun removeRealmKey() {
        mAppSharedPrefs.edit().remove(REALM_KEY).apply()
    }

    private fun getStringFromPrefs(aKey: String): String {
        return mAppSharedPrefs.getString(aKey, "")
    }

    private fun saveStringToPrefs(aKeyString: String) {
        mAppSharedPrefs.edit().putString(REALM_KEY, aKeyString).apply()
    }

    private fun encodeToString(aKey: ByteArray): String {
        Timber.d("Encoding Key: %s", Arrays.toString(aKey))
        return Base64.encodeToString(aKey, Base64.DEFAULT)
    }

    private fun decodeFromString(aSavedKey: String): ByteArray {
        val decoded = Base64.decode(aSavedKey, Base64.DEFAULT)
        Timber.d("Decoded Key: %s", Arrays.toString(decoded))
        return decoded
    }

    private fun generateKey(): ByteArray {
        val key = ByteArray(64)
        SecureRandom().nextBytes(key)
        return key
    }

    companion object {
        private val REALM_KEY = ".realm_key"
        private val SHARED_PREFS_NAME = "shared_prefs"

        private var sharedPrefsKeyProvider: SharedPrefsKeyProvider? = null

        fun getInstance(context: Context): SharedPrefsKeyProvider {
            if (sharedPrefsKeyProvider == null)
                sharedPrefsKeyProvider = SharedPrefsKeyProvider(context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE))

            return sharedPrefsKeyProvider!!
        }
    }
}
