package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import com.ahmedtawfik.kotlinappnavigation.model.entity.User

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUsers():List<User>
}