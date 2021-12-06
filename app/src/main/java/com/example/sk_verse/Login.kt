package com.example.sk_verse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.editEmailAddress)
        edtPassword=findViewById(R.id.editTextTextPass)
        btnLogin=findViewById(R.id.login_btn)
        btnSignUp=findViewById(R.id.sign_up_btn)
        btnSignUp.setOnClickListener {
            val intent= Intent(this@Login,Signup::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()
            login(email,password)

        }



    }
    private fun login(email: String, password: String){
     //login user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent= Intent(this@Login,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this@Login, "oops!, This User does not exist.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
}