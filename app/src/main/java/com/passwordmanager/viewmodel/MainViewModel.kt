package com.passwordmanager.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.passwordmanager.data.DataBase
import com.passwordmanager.data.DataBaseImpl
import com.passwordmanager.data.models.UserService

class MainViewModel : ViewModel(), View.OnClickListener {

    lateinit var dataBase: DataBase
    var addClicked: MutableLiveData<Void> = MutableLiveData()
    val servicesLoadedEvent: MutableLiveData<List<UserService>> = MutableLiveData()
    val updateEvent: MutableLiveData<Void> = MutableLiveData()
    var services: ArrayList<UserService> = arrayListOf()
    var currentItem: Int? = null

    fun onCreate() {
        dataBase = DataBaseImpl()
        services = dataBase.loadServices()
        servicesLoadedEvent.value = services
    }

    override fun onClick(v: View?) {
        addClicked.postValue(null)
    }

    fun itemAdded(service: UserService) {
        services.add(service)
        servicesLoadedEvent.value = services
    }

    fun itemUpdated(service: UserService) {
        services.set(currentItem!!, service)
        servicesLoadedEvent.value = services
    }

    fun itemRemoved() {
        services.removeAt(currentItem!!)
        servicesLoadedEvent.value = services
    }
}
