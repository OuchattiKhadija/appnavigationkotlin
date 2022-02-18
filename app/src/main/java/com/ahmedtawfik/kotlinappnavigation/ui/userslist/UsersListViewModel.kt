package com.ahmedtawfik.kotlinappnavigation.ui.userslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.DatabaseRepositoryImp
import com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.UserDatabase
import com.ahmedtawfik.kotlinappnavigation.model.remote.RemoteRepositoryImp
import com.ahmedtawfik.kotlinappnavigation.model.remote.RetroBuilder
import com.ahmedtawfik.kotlinappnavigation.model.remote.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel(app: Application) : AndroidViewModel(app) {

    private var localRepositoryImp: DatabaseRepositoryImp
    private var remoteRepositoryImp: RemoteRepositoryImp

    init {
        var db = UserDatabase.getInstance(app)
        localRepositoryImp = DatabaseRepositoryImp(db)

        var serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)

        remoteRepositoryImp = RemoteRepositoryImp(serviceInstance)
    }

    private var usersListMutableLiveData = MutableLiveData<List<User>>()

    val usersListLiveData: LiveData<List<User>> get() = usersListMutableLiveData

    private var usersAPIMutableLiveData = MutableLiveData<List<User>>()

    val usersAPILiveData: LiveData<List<User>> get() = usersAPIMutableLiveData

    private var addUserAPIMutableLiveData = MutableLiveData<User>()

    val addUserAPILiveData: LiveData<User> get() = addUserAPIMutableLiveData

    fun getUsersList() = viewModelScope.launch {
        usersListMutableLiveData.postValue(localRepositoryImp.getUsers())
    }

    fun getUsersAPI() = viewModelScope.launch {
        var result = remoteRepositoryImp.getAPIUsers()

        if (result.isSuccessful) {
            if (result.body() != null) {
                usersAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun addUserAPI(user: User) = viewModelScope.launch {
        var result = remoteRepositoryImp.addAPIUser(user)

        if (result.isSuccessful) {
            if (result.body() != null) {
                addUserAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.i("errMsg", result.message())
        }
    }

    fun deleteAPIUser(id: Int) {
        viewModelScope.launch {
            remoteRepositoryImp.deleteAPIUser(id)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            localRepositoryImp.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.deleteUser(user)
        }
    }

}