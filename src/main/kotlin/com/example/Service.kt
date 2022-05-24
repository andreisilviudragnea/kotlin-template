package com.example

interface Service {
    fun getUser(): User
}

class DefaultService(private val repository: Repository) : Service {
    override fun getUser(): User {
        return repository.getUser()
    }
}
