package com.passwordmanager.view.servidedialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.passwordmanager.R
import com.passwordmanager.databinding.ServiceDialogBinding
import com.passwordmanager.viewmodel.ServiceDialogViewModel
import android.text.Editable
import android.databinding.adapters.TextViewBindingAdapter.setPassword
import android.text.TextWatcher
import android.databinding.Bindable
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat.getSystemService
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.passwordmanager.App
import kotlinx.android.synthetic.main.service_dialog.view.*


open class ServiceDialogBase : DialogFragment(), ServiceDialog {

    lateinit var viewModel: ServiceDialogViewModel
    var serviceDialogBinding: ServiceDialogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ServiceDialogViewModel::class.java)
        viewModel.onCreate()


        viewModel.toastToShowEvent.observe(this, Observer { t -> showToast(t!!) })
        viewModel.loginErrorMessage.observe(this, Observer { t -> serviceDialogBinding!!.addserviceDialogLoginedittextLay.error = t })
        viewModel.nameErrorMessage.observe(this, Observer { t -> serviceDialogBinding!!.addserviceDialogNameedittextLay.error = t })
        viewModel.passwordErrorMessage.observe(this, Observer { t -> serviceDialogBinding!!.addserviceDialogPasswordedittextLay.error = t })
        viewModel.copiedEvent.observe(this, Observer { t ->
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = ClipData.newPlainText(t, t)
            clipboard.primaryClip = clip
        })

        viewModel.showHidePassword.observe(this, Observer { show ->
            if (show!!) {
                serviceDialogBinding?.addserviceDialogPasswordedittext?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                serviceDialogBinding?.addserviceDialogPasswordedittext?.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        serviceDialogBinding = DataBindingUtil.inflate(inflater, R.layout.service_dialog, container, false)

        serviceDialogBinding?.viewmodel = viewModel

        setTextWatchers()


        return serviceDialogBinding?.root
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun loginChanged(login: String) {

    }

    override fun passwordChanged(password: String) {

    }

    override fun actioBtnClicked() {
        dismiss()
    }

    override fun setBtnText(text: String) {
        serviceDialogBinding!!.addserviceDialogActionbutton.text = text
    }

    fun setTextWatchers() {
        serviceDialogBinding!!.root.addservice_dialog_loginedittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.loginChanged(p0.toString())
            }
        })

        serviceDialogBinding!!.root.addservice_dialog_passwordedittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.passwordChanged(p0.toString())
            }
        })

        serviceDialogBinding!!.root.addservice_dialog_nameedittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.nameChanged(p0.toString())

            }
        })
    }
}