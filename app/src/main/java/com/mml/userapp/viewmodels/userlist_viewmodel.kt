package com.mml.userapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mml.userapp.models.User
import com.mml.userapp.rest.APIClient
import com.mml.userapp.rest.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    var userList: MutableLiveData<List<User>?> = MutableLiveData()

    fun getUsers(): MutableLiveData<List<User>?> {
        return userList
    }

    fun connectApi(){
        var apiInterface = APIClient.client.create(APIInterface::class.java)

        val call2 = apiInterface.getUser()
        call2.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                call2.cancel()
            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                var body= response!!.body()
                userList.value=body
            }

        })
    }
}