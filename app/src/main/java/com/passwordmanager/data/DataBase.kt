package com.passwordmanager.data

import com.passwordmanager.data.models.Pair
import com.passwordmanager.data.models.UserService
import io.realm.Realm

import io.realm.RealmResults

interface DataBase {

    fun loadServices(): RealmResults<UserService>

    fun loadService(id: String): UserService?

    fun saveService(userService: UserService): Boolean

    fun deleteService(id: String): Boolean
}
