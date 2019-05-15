package com.passwordmanager.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.passwordmanager.R
import com.passwordmanager.data.models.UserService
import com.passwordmanager.databinding.ServiceItemBinding
import kotlinx.android.synthetic.main.service_item.view.*

class ServiceRVAdapter(private val items: List<UserService>, var listener: OnUserServiceClickedListener) : RecyclerView.Adapter<ServiceRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = ServiceItemBinding.inflate(inflater)
        binding.clickHandler = ClickHandler()
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items[p1])
    }


    inner class ViewHolder(val binding: ServiceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserService) {
            with(binding) {
                binding.serviceItemName.text = item.name
                binding.service = item
            }
        }
    }

    inner class ClickHandler {
        fun onClick(item: UserService) {
            listener.onServiceClicked(item.id)
        }
    }
}