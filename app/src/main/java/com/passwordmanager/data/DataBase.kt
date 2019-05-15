package com.passwordmanager.data

import com.passwordmanager.data.models.UserService

interface DataBase {

    fun loadServices(): ArrayList<UserService>

    fun loadService(id: String): UserService?

    fun saveService(userService: UserService): Boolean

    fun deleteService(id: String): Boolean
}
