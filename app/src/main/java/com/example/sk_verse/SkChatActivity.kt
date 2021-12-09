package com.example.sk_verse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class SkChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference

    var receiverRoom: String?= null
    var senderRoom: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name= intent.getStringExtra("name")
        val receiverUid= intent.getStringExtra("uid")


        val senderUid= FirebaseAuth.getInstance().currentUser?.uid
        mDbRef= FirebaseDatabase.getInstance().getReference()


        senderRoom= receiverUid + senderUid
        receiverRoom= senderUid + receiverUid

        supportActionBar?.title= name

        setContentView(R.layout.activity_sk_chat)

        messageBox= findViewById(R.id.messageBox)
        chatRecyclerView= findViewById(R.id.chatRecyclerView)
        sendButton= findViewById(R.id.sendButton)
        messageList= ArrayList()
        messageAdapter= MessageAdapter(this,messageList)

        chatRecyclerView.layoutManager= LinearLayoutManager(this)
        chatRecyclerView.adapter= messageAdapter

        //logic for adding message data to recycler view
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children){
                        val message =postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {

                }


            })

        //below adds message to database
        sendButton.setOnClickListener{
         val message= messageBox.text.toString()
         val messageObject= Message(message,senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")



        }




    }
}