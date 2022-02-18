package com.ahmedtawfik.kotlinappnavigation.model.remote

import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    @GET("/AhmedTawfik32/repo/users")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("/AhmedTawfik32/repo/users/{id}")
    suspend fun getAPIUser(@Path("id") id: Int): Response<User>

    @GET("/AhmedTawfik32/repo/users/")
    suspend fun getAPIUserQuery(@Query("id") id: Int): Response<User>

    @POST("/AhmedTawfik32/repo/users")
    suspend fun addAPIUser(@Body user: User): Response<User>

    @PUT("/AhmedTawfik32/repo/users/{id}")
    suspend fun updateAPIUser(@Body user: User, @Path("id") id: Int): Response<User>

    @DELETE("/AhmedTawfik32/repo/users/{id}")
    suspend fun deleteAPIUser(@Path("id") id: Int)

}