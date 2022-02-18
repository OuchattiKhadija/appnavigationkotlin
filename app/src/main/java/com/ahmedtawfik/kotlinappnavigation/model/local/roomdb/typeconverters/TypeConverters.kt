package com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.typeconverters

import androidx.room.TypeConverter
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toString(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun fromString(value: String): User {
        var userType = object : TypeToken<User>() {}.type
        return Gson().fromJson<User>(value, userType)
    }
}