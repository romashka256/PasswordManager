package com.passwordmanager.view.servidedialog

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ShowServiceDialog : ServiceDialogBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
            viewModel.setService(arguments?.get("serviceId") as String)

        viewModel.serviceLoaded.observe(this, Observer { service ->
            serviceDialogBinding?.addserviceDialogNameedittext?.setText(service!!.name)
            serviceDialogBinding?.addserviceDialogLoginedittext?.setText(service!!.pair!!.login)
            serviceDialogBinding?.addserviceDialogPasswordedittext?.setText(service!!.pair!!.password)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        serviceDialogBinding?.addserviceDialogActionbutton?.setOnClickListener { actioBtnClicked() }
        setBtnText("Удалить")

        return v
    }

    override fun actioBtnClicked() {
        super.actioBtnClicked()
        viewModel.onDeleteClicked()
    }

    override fun setBtnText(text: String) {
        return super.setBtnText(text)
    }
}