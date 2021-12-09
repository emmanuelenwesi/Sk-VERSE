package com.example.sk_verse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SkChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    val receiverRoom: String?= null
    val senderRoom: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name= intent.getStringExtra("name")
        val receiverUid= intent.getStringExtra("uid")
        val senderUid= intent.getStringExtra("uid")
        supportActionBar?.title= name

        setContentView(R.layout.activity_sk_chat)
        messageBox= findViewById(R.id.messageBox)
        chatRecyclerView= findViewById(R.id.chatRecyclerView)
        sendButton= findViewById(R.id.sendButton)
        messageList= ArrayList()
        messageAdapter= MessageAdapter(this,messageList)
        sendButton.setOnClickListener{


        }




    }
}