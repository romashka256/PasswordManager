package com.passwordmanager.data.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*
import android.databinding.BindingAdapter
import android.view.View
import com.passwordmanager.utils.Validator
import io.realm.annotations.Ignore

open class UserService : RealmObject() {

    var name: String = ""
    var pair: Pair? = Pair()
    @field:PrimaryKey
    var id: String = UUID.randomUUID().toString()

}
