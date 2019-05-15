package com.passwordmanager.view.servidedialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AddServiceDialog : ServiceDialogBase() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = super.onCreateView(inflater, container, savedInstanceState)

        serviceDialogBinding?.addserviceDialogActionbutton?.setOnClickListener { actioBtnClicked() }
        setBtnText("Добавить")

        return v
    }

    override fun actioBtnClicked() {
        if(viewModel.onAddClicked())
        super.actioBtnClicked()
    }

    override fun setBtnText(text: String) {
        return super.setBtnText(text)
    }
}