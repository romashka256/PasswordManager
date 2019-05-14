package com.passwordmanager.data

import com.passwordmanager.data.models.Entry
import com.passwordmanager.data.models.Service

import io.realm.RealmResults

interface DataBase {

    val services: RealmResults<Service>

    fun addEntry(service: Service, entry: Entry)

}
