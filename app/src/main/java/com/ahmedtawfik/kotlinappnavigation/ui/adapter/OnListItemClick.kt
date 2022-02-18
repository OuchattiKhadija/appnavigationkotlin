package com.ahmedtawfik.kotlinappnavigation.ui.adapter

import com.ahmedtawfik.kotlinappnavigation.model.entity.User


interface OnListItemClick {
    fun onItemClick(user: User)
}