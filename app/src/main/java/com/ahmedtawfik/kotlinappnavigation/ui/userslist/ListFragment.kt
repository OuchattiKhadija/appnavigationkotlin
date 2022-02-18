package com.ahmedtawfik.kotlinappnavigation.ui.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.ahmedtawfik.kotlinappnavigation.ui.R
import com.ahmedtawfik.kotlinappnavigation.ui.adapter.OnListItemClick
import com.ahmedtawfik.kotlinappnavigation.ui.adapter.UserRecyclerView
import com.ahmedtawfik.kotlinappnavigation.ui.databinding.FragmentListBinding

class ListFragment : Fragment(), OnListItemClick {

    lateinit var binding: FragmentListBinding

    lateinit var viewModel: UsersListViewModel

    var userName: String? = null

    private val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)

        userName = arguments?.getString("userName")

        binding.rvShowData.adapter = userRecyclerView

//        Log.i("user01",R.drawable.user01.toString())

        getAllUsers()

        binding.btnAdd.setOnClickListener {

            var msg = binding.edtMessage.text.toString()

           viewModel.addUserAPI(
               User(
                   10,
                   userName.toString(),
                   msg,
                   R.drawable.userlogin
               )
           )

            getAllUsers()

            binding.edtMessage.setText("")
        }

        userRecyclerView.onListItemClick = this

        viewModel.usersAPILiveData.observe(viewLifecycleOwner,
            Observer {
                if (it != null) {
                    userRecyclerView.setList(it)
                    binding.progressBar.visibility = View.GONE
                }else{
                    Toast.makeText(requireContext(), "Connection Failed",Toast.LENGTH_LONG).show()
                }
            }
        )

        viewModel.addUserAPILiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(requireContext(), "The user ${it.name} is added successfully",Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText(requireContext(), "Connection Failed",Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun getAllUsers() {
        viewModel.getUsersAPI()
        binding.progressBar.visibility = View.VISIBLE
//        viewModel.getUsersList()
    }

    override fun onItemClick(user: User) {
//        viewModel.deleteUser(user)

        viewModel.deleteAPIUser(user.id)

        Toast.makeText(
            context,
            "The user ${user.name} is deleted successfully",
            Toast.LENGTH_SHORT
        ).show()

        getAllUsers()
    }
}