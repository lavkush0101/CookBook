package com.example.cookbook.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.example.cookbook.R
import com.example.cookbook.modal.CuisineModal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() ,View.OnClickListener {
    lateinit var americanCardview: RelativeLayout
    lateinit var indianCard: RelativeLayout
    lateinit var englishCard: RelativeLayout
    lateinit var chinessCard: RelativeLayout
    lateinit var italinCard: RelativeLayout
    lateinit var asianCard: RelativeLayout
    lateinit var selectCuisines: Button

    var amrican_click_value = 0
    var indian_click_value = 0
    var english_click_value = 0
    var chiness_click_value = 0
    var italinan_click_value = 0
    var asian_click_value = 0

    private lateinit var  auth:FirebaseAuth

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        auth= FirebaseAuth.getInstance()
        americanCardview = findViewById(R.id.american_card_one)
         indianCard       = findViewById(R.id.indian_card)
         englishCard      = findViewById(R.id.english)
         chinessCard      = findViewById(R.id.chinese)
         italinCard       = findViewById(R.id.italin_card)
         asianCard        = findViewById(R.id.asian_card)

         selectCuisines        = findViewById(R.id.select_cuisines)

        americanCardview.setOnClickListener(this)
        indianCard.setOnClickListener(this)
        englishCard.setOnClickListener(this)
        chinessCard.setOnClickListener(this)
        italinCard.setOnClickListener(this)
        asianCard.setOnClickListener(this)
        selectCuisines.setOnClickListener(this)

        }

    override fun onClick(v: View?) {
         when(v?.id){
            R.id.american_card_one ->{
                if (amrican_click_value==0) {
                    amrican_click_value++;
                    americanCardview.setBackgroundColor(Color.GREEN)
                }else{
                    amrican_click_value=0;
                    americanCardview.setBackgroundColor(Color.WHITE)
                }
            }
         R.id.indian_card ->{
             if (indian_click_value==0) {
                 indian_click_value++;
                 indianCard.setBackgroundColor(Color.GREEN)
             }else{
                 indian_click_value=0;
                 indianCard.setBackgroundColor(Color.WHITE)
             }
         }
         R.id.english ->{
             if (english_click_value==0) {
                 english_click_value++;
                 englishCard.setBackgroundColor(Color.GREEN)
             }else{
                 english_click_value=0;
                 englishCard.setBackgroundColor(Color.WHITE)

             }
         }
         R.id.chinese ->{
             if (chiness_click_value==0) {
                 chiness_click_value++;
                 chinessCard.setBackgroundColor(Color.GREEN)
             }else{
                 chiness_click_value=0;
                 chinessCard.setBackgroundColor(Color.WHITE)

             }
         }
         R.id.italin_card ->{
             if (italinan_click_value==0) {
                 italinan_click_value++;
                 italinCard.setBackgroundColor(Color.GREEN)
             }else{
                 italinan_click_value=0;
                 italinCard.setBackgroundColor(Color.WHITE)

             }
         }
         R.id.asian_card ->{
             if (asian_click_value==0) {
                 asian_click_value++;
                 asianCard.setBackgroundColor(Color.GREEN)
             }else{
                 asian_click_value=0;
                 asianCard.setBackgroundColor(Color.WHITE)

             }
         }
         R.id.select_cuisines ->{

             Select();
             }
        }

    }

    private fun Select() {
       var indiaCard:String?=null
       var asianCard:String?=null
       var chiness:String?=null
       var americanCard:String?=null
       var englisCard:String?=null
       var itianCard:String?=null
        if (indian_click_value==1){
             indiaCard="india"
        }
        if (asian_click_value==1){
            asianCard="asia"

        }
        if (chiness_click_value==1){
             chiness="Chiness"

        }
        if (amrican_click_value==1){
            americanCard="america"

        }
        if (english_click_value==1){
            englisCard="english"

        }
        if (italinan_click_value==1){
             itianCard="itly"

        }
        val firebaseUser: FirebaseUser? =auth.currentUser
       val uid:String= firebaseUser?.uid.toString()
       val cuisineModal: CuisineModal = CuisineModal(
           "",
           americanCard,
           indiaCard,
           itianCard,
           chiness,
           englisCard,
           asianCard)

       database = Firebase.database.reference
       database.child("users").child("CookBook").setValue(cuisineModal);

       val intent = Intent(this, Recipes::class.java)
        startActivity(intent)
    }

    fun Logout(view: View) {

//        Firebase.auth.signOut()
    }
}