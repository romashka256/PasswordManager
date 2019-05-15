package com.passwordmanager.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.passwordmanager.data.models.UserService
import com.passwordmanager.databinding.ServiceItemBinding


class ServiceRVAdapter(private val items: List<UserService>, var listener: OnUserServiceClickedListener) : RecyclerView.Adapter<ServiceRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, pos: Int): ViewHolder {
        val inflater = LayoutInflater.from(container.context)
        val binding = ServiceItemBinding.inflate(inflater, container, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items[p1], p1)
        p0.itemView.setOnClickListener {
            listener.onServiceClicked(items[p1].id, p1)
        }
    }

    inner class ViewHolder(val binding: ServiceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserService, pos: Int) {
            with(binding) {
                binding.service = item
            }
        }
    }
}