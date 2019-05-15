package com.passwordmanager.viewmodel

import android.app.Service
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.passwordmanager.data.DataBase
import com.passwordmanager.data.DataBaseImpl
import com.passwordmanager.data.models.UserService
import com.passwordmanager.utils.Validator
import android.view.View


open class ServiceDialogViewModel : ViewModel() {

    val toastToShowEvent: MutableLiveData<String> = MutableLiveData()

    lateinit var userService: ObservableField<UserService>

    lateinit var dataBase: DataBase

    private var passwordHiden: Boolean = false

    val loginErrorMessage: MutableLiveData<String> = MutableLiveData()
    val serviceLoaded: MutableLiveData<UserService> = MutableLiveData()
    val nameErrorMessage: MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage: MutableLiveData<String> = MutableLiveData()
    val copiedEvent: MutableLiveData<String> = MutableLiveData()
    val showHidePassword: MutableLiveData<Boolean> = MutableLiveData()

    fun onCreate() {
        userService = ObservableField(UserService())
        dataBase = DataBaseImpl()
    }

    fun setService(id: String) {
        val loaded: UserService? = dataBase.loadService(id)

        if (loaded != null)
            userService.set(loaded)
        else
            userService.set(UserService())

        serviceLoaded.value = userService.get()
    }

    fun loginChanged(login: String) {
        if (Validator.valideLogin(login)) {
            userService.get()?.pair?.login = login
            loginErrorMessage.value = ""
        } else
            loginErrorMessage.value = "Некорректный логин (@)"
    }

    fun passwordChanged(password: String) {
        if (Validator.validePassword(password)) {
            userService.get()?.pair?.password = password
            passwordErrorMessage.value = ""
        } else
            passwordErrorMessage.value = "Пароль должен быть больше 6 знаков"
    }

    fun nameChanged(name: String) {
        if (Validator.valideName(name)) {
            userService.get()?.name = name
            nameErrorMessage.value = ""
        } else
            nameErrorMessage.value = "Некорректное название"
    }

    fun onAddClicked(): Boolean {
        return if (Validator.valideName(userService.get()?.name) && Validator.valideLogin(userService.get()?.pair?.login) && Validator.validePassword(userService.get()?.pair?.password)) {
            if (dataBase.saveService(userService.get()!!))
                toastToShowEvent.value = "Успешно добавлено"
            else
                toastToShowEvent.value = "Произошла ошибка"
            true
        } else
            false
    }

    fun onDeleteClicked() {
        if (dataBase.deleteService(userService.get()!!.id))
            toastToShowEvent.value = "Успешно удалено"
        else
            toastToShowEvent.value = "Произошла ошибка"
    }

    fun copyLogin(v: View) {
        copiedEvent.value = userService.get()?.pair?.login
        toastToShowEvent.value = "Скопировано"
    }

    fun copyPassword(v: View) {
        copiedEvent.value = userService.get()?.pair?.password
        toastToShowEvent.value = "Скопировано"
    }

    fun showHidePassword(v: View) {
        passwordHiden = !passwordHiden
        showHidePassword.value = passwordHiden
    }
}