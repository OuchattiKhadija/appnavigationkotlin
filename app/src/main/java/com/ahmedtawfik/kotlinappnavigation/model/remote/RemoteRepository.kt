package com.ahmedtawfik.kotlinappnavigation.model.remote

import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import retrofit2.Response

interface RemoteRepository {
    suspend fun getAPIUsers(): Response<List<User>>

    suspend fun getAPIUser(id: Int): Response<User>

    suspend fun getAPIUserQuery(id: Int): Response<User>

    suspend fun addAPIUser(user: User): Response<User>

    suspend fun updateAPIUser(user: User, id: Int): Response<User>

    suspend fun deleteAPIUser(id: Int)

}