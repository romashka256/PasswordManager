package com.passwordmanager.view.servidedialog

import android.view.View

interface ServiceDialog {
    fun loginChanged(login: String)
    fun passwordChanged(password: String)
    fun actioBtnClicked()
    fun setBtnText(text:String)
}