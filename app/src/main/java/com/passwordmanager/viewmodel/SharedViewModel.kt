package com.passwordmanager.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.passwordmanager.data.models.UserService

class SharedViewModel : ViewModel() {
    val userServiceUpdatedEvent: MutableLiveData<UserService> = MutableLiveData()
    val userServiceAddedEvent: MutableLiveData<UserService> = MutableLiveData()
    val userServiceDeleteEvent: MutableLiveData<Void> = MutableLiveData()
}