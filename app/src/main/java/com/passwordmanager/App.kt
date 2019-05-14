package com.passwordmanager

import android.app.Application
import com.passwordmanager.data.SharedPrefsKeyProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

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
