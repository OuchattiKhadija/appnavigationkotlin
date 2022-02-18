package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import androidx.room.*
import com.ahmedtawfik.kotlinappnavigation.model.entity.User

@Dao
interface UserDAO {

    @Query("select * from user_table")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}