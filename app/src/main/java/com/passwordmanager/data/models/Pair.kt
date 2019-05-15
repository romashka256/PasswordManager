package com.passwordmanager.data.models

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*
import javax.annotation.PropertyKey

@RealmClass
open class Pair(var login: String,
                var password: String, @field:PrimaryKey
                private var id: String = UUID.randomUUID().toString()) : RealmModel {

    constructor() : this(login = "", password = "")
}
