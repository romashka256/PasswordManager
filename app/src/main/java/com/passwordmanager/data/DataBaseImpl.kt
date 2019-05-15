package com.passwordmanager.data

import com.passwordmanager.data.models.UserService
import io.realm.Realm
import timber.log.Timber

class DataBaseImpl : DataBase {

    private var realm: Realm = Realm.getDefaultInstance()

    override fun loadServices(): ArrayList<UserService> {
        val services = realm.where(UserService::class.java).findAll()
        return realm.copyFromRealm(services) as ArrayList<UserService>? ?: arrayListOf<UserService>()
    }

    override fun loadService(id: String): UserService? {
        realm = Realm.getDefaultInstance()
        return try {
            realm.beginTransaction()
            val service = realm.where(UserService::class.java).equalTo("id", id).findAll().first()
            realm.commitTransaction()
            return realm.copyFromRealm(service)
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
