package com.ahmedtawfik.kotlinappnavigation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

//    override fun onClick(view: View?) {
//
//        when (view!!.id) {
//            R.id.btn_login -> {
//                Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_LONG).show()
//            }
//
//        }
//    }


//    fun onClickLogin(view: View) {
//        Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_LONG).show()
//    }


