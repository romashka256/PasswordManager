package com.passwordmanager

import android.app.Application
import android.content.Context
import com.passwordmanager.data.SharedPrefsKeyProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        Realm.init(applicationContext)

        val keyProvider = SharedPrefsKeyProvider.getInstance(applicationContext)

        val mRealmConfiguration = RealmConfiguration.Builder()
                .name(Constants.RealmDBName)
                .encryptionKey(keyProvider.realmKey)
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(mRealmConfiguration)
    }
}
