package com.passwordmanager.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.passwordmanager.R
import com.passwordmanager.databinding.MainFragmentBinding
import com.passwordmanager.view.servidedialog.AddServiceDialog
import com.passwordmanager.view.servidedialog.ShowServiceDialog
import com.passwordmanager.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.onCreate()

        mainViewModel.addClicked.observe(this, Observer { v -> AddServiceDialog().show(activity?.supportFragmentManager, "asd") })
      //  mainViewModel., observe(this, Observer { v -> AddServiceDialog().show(activity?.supportFragmentManager, "asd") })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(context).inflate(R.layout.main_fragment, null, false)

        mainFragmentBinding = MainFragmentBinding.bind(view)

        mainFragmentBinding.viewmodel = mainViewModel

        mainFragmentBinding.mainfragmentRv.layoutManager = LinearLayoutManager(context)

        mainViewModel.servicesLoadedEvent.observe(this, Observer { list ->
            mainFragmentBinding.mainfragmentRv.adapter = ServiceRVAdapter(list!!,object : OnUserServiceClickedListener{
                override fun onServiceClicked(id: String) {
                    val bundle = Bundle()
                    bundle.putString("serviceId", id)
                    val dialog = ShowServiceDialog()
                    dialog.arguments = bundle

                    dialog.show(activity?.supportFragmentManager, "asd")
                }
            })
        })

        return view
    }


}
