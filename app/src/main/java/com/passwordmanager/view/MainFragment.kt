package com.passwordmanager.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.passwordmanager.R
import com.passwordmanager.databinding.MainFragmentBinding
import com.passwordmanager.view.servidedialog.AddServiceDialog
import com.passwordmanager.view.servidedialog.ShowServiceDialog
import com.passwordmanager.viewmodel.MainViewModel
import com.passwordmanager.viewmodel.SharedViewModel


class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var listAdapter: ServiceRVAdapter
    private val fragment: Fragment = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.onCreate()

        mainViewModel.addClicked.observe(this, Observer {
            val dialog = AddServiceDialog()
            dialog.setTargetFragment(fragment, 0)
            dialog.show(fragmentManager, "asd123")
        })

        mainViewModel.updateEvent.observe(this, Observer { pos ->
            listAdapter.notifyItemChanged(pos!!)
        })

        mainViewModel.deleteEvent.observe(this, Observer { pos ->
            listAdapter.notifyItemRemoved(pos!!)
        })

        sharedViewModel.userServiceAddedEvent.observe(this, Observer { item -> mainViewModel.itemAdded(item!!) })
        sharedViewModel.userServiceUpdatedEvent.observe(this, Observer { item -> mainViewModel.itemUpdated(item!!) })
        sharedViewModel.userServiceDeleteEvent.observe(this, Observer { item -> mainViewModel.itemRemoved() })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.main_fragment, null, false)

        mainFragmentBinding = MainFragmentBinding.bind(view)

        mainFragmentBinding.viewmodel = mainViewModel

        val layoutManager = LinearLayoutManager(context)

        mainFragmentBinding.mainfragmentRv.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(mainFragmentBinding.mainfragmentRv.context,
                layoutManager.orientation)
        mainFragmentBinding.mainfragmentRv.addItemDecoration(dividerItemDecoration)

        mainViewModel.servicesLoadedEvent.observe(this, Observer { list ->
            listAdapter = ServiceRVAdapter(list!!, object : OnUserServiceClickedListener {
                override fun onServiceClicked(id: String, pos: Int) {
                    mainViewModel.currentItem = pos
                    val bundle = Bundle()
                    bundle.putString("serviceId", id)
                    val dialog = ShowServiceDialog()
                    dialog.arguments = bundle
                    dialog.setTargetFragment(fragment, 0)
                    dialog.show(fragmentManager, "asd")
                }
            })
            mainFragmentBinding.mainfragmentRv.adapter = listAdapter
        })
        return view
    }
}
