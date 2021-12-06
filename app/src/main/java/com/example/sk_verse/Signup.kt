package com.example.sk_verse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtName: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edtEmail=findViewById(R.id.editEmailAddress)
        edtPassword=findViewById(R.id.editTextTextPass)
        edtName=findViewById(R.id.username)
        btnSignUp=findViewById(R.id.sign_up_btn)
        btnSignUp.setOnClickListener {
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()

            signup(email,password)
        }
    }
    private fun signup(email: String, password: String){
        //creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                  val intent= Intent(this@Signup,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this@Signup, "Some error occured.",Toast.LENGTH_SHORT).show()

                }
            }

    }
}