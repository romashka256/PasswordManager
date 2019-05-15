package com.passwordmanager.view.servidedialog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.passwordmanager.viewmodel.SharedViewModel

class AddServiceDialog : ServiceDialogBase() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = super.onCreateView(inflater, container, savedInstanceState)

        serviceDialogBinding?.addserviceDialogActionbutton?.setOnClickListener { actioBtnClicked() }
        setBtnText("Добавить")

        sharedViewModel = ViewModelProviders.of(targetFragment!!).get(SharedViewModel::class.java)

        return v
    }

    override fun actioBtnClicked() {
        if (viewModel.onAddClicked()) {
            super.actioBtnClicked()
            sharedViewModel.userServiceAddedEvent.value = viewModel.userService.get()
        }
    }
}