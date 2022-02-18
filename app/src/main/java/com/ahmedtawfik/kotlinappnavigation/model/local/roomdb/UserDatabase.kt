package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.typeconverters.Converters

private const val DATABASE_NAME = "user_database"

@TypeConverters(value = [Converters::class])
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return instance
                ?: synchronized(Any()) {
                    instance ?: buildDatabase(
                        context
                    ).also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): UserDatabase {
            return Room
                .databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
        }
    }

}