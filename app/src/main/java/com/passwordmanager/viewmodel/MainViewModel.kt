package com.passwordmanager.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.provider.ContactsContract
import android.view.View
import com.passwordmanager.App
import com.passwordmanager.data.DataBase
import com.passwordmanager.data.DataBaseImpl
import com.passwordmanager.data.models.UserService

class MainViewModel : ViewModel(), View.OnClickListener {

    lateinit var dataBase: DataBase

    var addClicked: MutableLiveData<Void> = MutableLiveData()

    val servicesLoadedEvent:MutableLiveData<List<UserService>> = MutableLiveData()

    fun onCreate() {
        dataBase = DataBaseImpl()

        servicesLoadedEvent.value = dataBase.loadServices().toList()
    }

    override fun onClick(v: View?) {
        addClicked.postValue(null)
    }
}
