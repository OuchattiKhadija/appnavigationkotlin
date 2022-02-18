package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepositoryImp(private val db: UserDatabase) : DatabaseRepository {
    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().insertOrUpdateUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().deleteUser(user)
        }
    }

    override suspend fun getUsers() = withContext(Dispatchers.IO) {
        db.userDao().getUsers()
    }
}