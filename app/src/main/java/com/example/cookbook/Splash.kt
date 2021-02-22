package com.example.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.cookbook.Activity.LoginUser
import com.example.cookbook.Activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Splash : AppCompatActivity() {
     lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
         auth=Firebase.auth
        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = auth.currentUser
            if(currentUser != null){
                val dashBoadr:Intent= Intent(this, MainActivity::class.java)
                startActivity(dashBoadr)

            } else{
                val loginScreen:Intent= Intent(this, LoginUser::class.java)
                startActivity(loginScreen)

            }
        }, 8000)
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){

        }
    }
}