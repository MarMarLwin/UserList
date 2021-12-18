package com.mml.userapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mml.userapp.R
import com.mml.userapp.listeners.AdapterCallback
import com.mml.userapp.models.User
import kotlinx.android.synthetic.main.user_list_item.view.*


class UserListAdapter(private val userList: List<User>,val callback: AdapterCallback) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(userList[position],callback)
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var userName: TextView
        lateinit var cardView: CardView
        lateinit var emailView: TextView

        fun bindItem(user: User,callback: AdapterCallback) {
            userName = itemView.usernameTxt
            emailView=itemView.emailTxt
            cardView = itemView.cardView
            userName.text = user.name
            emailView.text=user.email

            cardView.setOnClickListener {
                try{
                  /*  val infoActivity = it.Intent(this, UserInfoActivity::class.java)
//                mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(infoActivity)*/
                    callback.onMethodCallback(user)
                }catch (e:Exception){
                    Log.e("error",e.message.toString())
                }
            }

        }

    }

}