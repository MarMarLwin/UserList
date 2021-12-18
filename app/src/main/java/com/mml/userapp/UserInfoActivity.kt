package com.mml.userapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mml.userapp.databinding.ActivityUserInfoBinding
import com.mml.userapp.models.User

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_user_info)
        //setContentView(R.layout.activity_user_info)
        val userInfo=intent.getSerializableExtra("userInfo") as User
        supportActionBar !!.title = "${userInfo.name}\'s information";
        binding.user=userInfo;





    }
}