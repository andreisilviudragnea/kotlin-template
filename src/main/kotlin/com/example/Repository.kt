package com.example

data class User(val name: String)

interface Repository {
    fun getUser(): User
}
