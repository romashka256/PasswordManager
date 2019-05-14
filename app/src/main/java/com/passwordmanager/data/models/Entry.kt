package com.passwordmanager.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

class Entry(@field:Required
            var login: String?, @field:Required
            var password: String?) : RealmObject() {

    @PrimaryKey
    private val id: String

    init {
        this.id = UUID.randomUUID().toString()
    }
}
