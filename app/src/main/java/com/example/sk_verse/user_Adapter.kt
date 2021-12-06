package com.example.sk_verse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class userAdapter(val context: Context, val userList: ArrayList<Users>):
    RecyclerView.Adapter<userAdapter.userViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
      val view : View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
       return  userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
     return userList.size
    }
    class userViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName= itemView.findViewById<TextView>(R.id.txt_name)



    }


}