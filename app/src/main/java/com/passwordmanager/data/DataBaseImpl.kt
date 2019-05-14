package com.passwordmanager.data

import com.passwordmanager.data.models.Entry
import com.passwordmanager.data.models.Service

import io.realm.Realm
import io.realm.RealmResults

class DataBaseImpl : DataBase {

    private val realm: Realm = Realm.getDefaultInstance()

    override val services: RealmResults<Service> = realm.where<Service>(Service::class.java).findAllAsync()

    override fun addEntry(service: Service, entry: Entry) {
        realm.beginTransaction()

        realm.copyToRealm(entry)
        service.entry = entry

        realm.commitTransaction()
    }
}
