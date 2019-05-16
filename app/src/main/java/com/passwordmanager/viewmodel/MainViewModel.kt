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
    val updateEvent: MutableLiveData<Int> = MutableLiveData()
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
        updateEvent.value = services.size - 1
    }

    fun itemUpdated(service: UserService) {
        services[currentItem!!] = service
        updateEvent.value = currentItem
    }

    fun itemRemoved() {
        services.removeAt(currentItem!!)
        updateEvent.value = currentItem
    }
}
