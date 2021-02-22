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

class SignUpUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var TAG:String ="TAG"

    private lateinit var emailUserS:EditText
    private lateinit var passwordUserS:EditText
    private lateinit var buttonSubmitS:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_user)

        auth = Firebase.auth

        emailUserS=findViewById(R.id.email_user_s)
        passwordUserS=findViewById(R.id.password_user_s)
        buttonSubmitS=findViewById(R.id.button_Submit_s)

        buttonSubmitS.setOnClickListener {
            validateInputFieldEmailAndPAssword()
        }
    }

    private fun validateInputFieldEmailAndPAssword(){
        val email:String =emailUserS.text.toString().trim()
        val password:String =passwordUserS.text.toString().trim()

        if (email.isEmpty()){
            emailUserS.setError("Enter the corret email")
        }
        if (password.isEmpty()){
            passwordUserS.setError("Enter the password ")

        }
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "please fill the email & password", Toast.LENGTH_SHORT).show()
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        emailUserS.setText("")
                        passwordUserS.setText("")

                        val dashBoadr:Intent= Intent(this, MainActivity::class.java)
                        startActivity(dashBoadr)

                        Toast.makeText(this, " Login Successfull", Toast.LENGTH_SHORT).show()

                        val user = auth.currentUser
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }

        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
//            reload();
        }
    }

    private fun LoginScren(view: View) {
        val intent = Intent(this, LoginUser::class.java)
        startActivity(intent)
    }
}