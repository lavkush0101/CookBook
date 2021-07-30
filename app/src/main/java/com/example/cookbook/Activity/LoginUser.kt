package com.example.cookbook.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cookbook.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginUser : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailUserL: EditText
    private lateinit var passwordUserL: EditText
    private lateinit var buttonSubmitL: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)

        auth = Firebase.auth

        emailUserL=findViewById(R.id.email_user_l)
        passwordUserL=findViewById(R.id.password_user_l)
        buttonSubmitL=findViewById(R.id.button_Submit_l)

        buttonSubmitL.setOnClickListener {
            validateInputFieldEmailAndPAssword()
        }


    }
    private fun validateInputFieldEmailAndPAssword(){
        val email:String =emailUserL.text.toString().trim()
        val password:String =passwordUserL.text.toString().trim()

        if (email.isEmpty()){
            emailUserL.setError("Enter the corret email")
        }
        if (password.isEmpty()){
            passwordUserL.setError("Enter the password ")
        }
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "please fill the email & password", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signInWithEmail:success")
                        val dashBoadr:Intent= Intent(this, MainActivity::class.java)
                        startActivity(dashBoadr)

                    } else {
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
        }
    }

    fun SignUpUser(view: View) {
        val intent = Intent(this, SignUpUser::class.java)
        startActivity(intent)

    }
    //keytool -exportcert -alias YOUR_RELEASE_KEY_ALIAS -keystore "C:\Users\Luvkush\.android\debug.keystore"| "C:\openssl-0.9.8k_X64\bin\openssl" sha1 -binary | "C:\openssl-0.9.8k_X64\bin\openssl" base64
    
}
