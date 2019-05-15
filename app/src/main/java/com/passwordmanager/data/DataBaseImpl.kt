package com.passwordmanager.data

import com.passwordmanager.data.models.Pair
import com.passwordmanager.data.models.UserService

import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber
import java.lang.Exception

class DataBaseImpl : DataBase {

    private var realm: Realm = Realm.getDefaultInstance()

    override fun loadServices(): RealmResults<UserService> {
        var services = realm.where<UserService>(UserService::class.java).findAll()
        return services
    }

    override fun loadService(id: String): UserService? {
        realm = Realm.getDefaultInstance()
        return try {
            realm.beginTransaction()
            val service = realm.where(UserService::class.java).equalTo("id", id).findAll().first()
            realm.commitTransaction()
            realm.copyFromRealm(service)
        } catch (e: Exception) {
            realm.close()
            null
        }
    }

    override fun saveService(userService: UserService): Boolean {
        Timber.i("saving : %s", userService.toString())
        realm = Realm.getDefaultInstance()
        return try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(userService)
            realm.commitTransaction()
            true
        } catch (e: Exception) {
            realm.close()
            false
        }
    }

    override fun deleteService(id: String): Boolean {
        realm = Realm.getDefaultInstance()
        return try {
            realm.beginTransaction()
            realm.where(UserService::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
            realm.commitTransaction()
            true
        } catch (e: Exception) {
            realm.close()
            false
        }
    }
}
