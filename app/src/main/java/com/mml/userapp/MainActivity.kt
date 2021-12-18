package com.mml.userapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mml.userapp.adapters.UserListAdapter
import com.mml.userapp.common.Utils
import com.mml.userapp.data_access.DatabaseAccess
import com.mml.userapp.listeners.AdapterCallback
import com.mml.userapp.models.User
import com.mml.userapp.viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.uiThread
import java.io.Serializable

class MainActivity : AppCompatActivity(), AdapterCallback {
    lateinit var adapter: UserListAdapter
    private  var userList:List<User> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar !!.title= "User List"
        val viewModel = of(this).get(UserListViewModel::class.java)
        val progressDialog = Utils.makeProgressDialog(this, "Loading.....")
        progressDialog.show()
        if(!Utils.isNetworkConnected(applicationContext)!!){
            Toast.makeText(applicationContext,"Please check your internet connection",Toast.LENGTH_SHORT).show()

            progressDialog.show()
        }

        else{

            viewModel.connectApi()
            viewModel.getUsers().observe(this, Observer {
                if(it!!.isNotEmpty())
                {
                    try{
                        userList=it
                        progressDialog.dismiss()
                        val gson= GsonBuilder().create()
                        val db= DatabaseAccess.getAppDatabase(this)!!.userDao()
                        db.insert(userList)

                        var list= listOf<User>()
                        doAsync {
                            list=db.getAllUser()
                            uiThread {
                                setRecyclerView(list)
                            }
                        }
                    }catch (e:Exception){
                        throw e
                        progressDialog.dismiss();
                    }
                }
            })
        }

    }
    private fun setRecyclerView(list: List<User>) {
        try {

            adapter = UserListAdapter(list,this)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter

        } catch (e: Exception) {
            throw e
        }
    }

    override fun onMethodCallback(user: User) {
        try{
            val userInfoActivity = Intent(this, UserInfoActivity::class.java)
//                mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
            userInfoActivity.putExtra("userInfo",user as Serializable)
            startActivity(userInfoActivity)
        }catch (e:java.lang.Exception){
            Log.e("error",e.message.toString())
        }
    }
}