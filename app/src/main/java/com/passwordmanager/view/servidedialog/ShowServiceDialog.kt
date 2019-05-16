package com.passwordmanager.view.servidedialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.passwordmanager.viewmodel.SharedViewModel
import timber.log.Timber

class ShowServiceDialog : ServiceDialogBase() {
    var delete: Boolean = false

    private lateinit var sharedViewModel: SharedViewModel

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

        sharedViewModel = ViewModelProviders.of(targetFragment!!).get(SharedViewModel::class.java)

        serviceDialogBinding?.addserviceDialogActionbutton?.setOnClickListener { actioBtnClicked() }
        setBtnText("Удалить")

        return v
    }

    override fun actioBtnClicked() {
        viewModel.onDeleteClicked()
        sharedViewModel.userServiceDeleteEvent.value = null
        delete = true
        super.actioBtnClicked()

    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop")
        if (!delete) {
            viewModel.save()
            sharedViewModel.userServiceUpdatedEvent.value = viewModel.userService.get()
        }
    }
}