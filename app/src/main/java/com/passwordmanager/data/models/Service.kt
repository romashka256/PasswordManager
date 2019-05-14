package com.passwordmanager.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

class Service(var name: String?, var entry: Entry?) : RealmObject() {
    @PrimaryKey
    private val id: String = UUID.randomUUID().toString()
}
