package com.ahmedtawfik.kotlinappnavigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmedtawfik.kotlinappnavigation.ui.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentLoginBinding

    //Global Variables
    val userName: String = "Ahmed"
    val password: String = "Ahmed123"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {

            if (!binding.edtUserName.text.toString()
                    .isNullOrEmpty() && !binding.edtPassword.text.toString().isNullOrEmpty()
            ) {
                if (binding.edtUserName.text.toString()
                        .equals(userName) && binding.edtPassword.text.toString().equals(password)
                ) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()

                   var action =LoginFragmentDirections.actionLoginFragmentToListFragment(binding.edtUserName.text.toString())

                    findNavController().navigate(action)

//                    var intent: Intent = Intent(this, SecondActivity::class.java)
//                    intent.putExtra("UserName", binding.edtUserName.text.toString())
//                    startActivity(intent)
                }
            }

        }
    }
}