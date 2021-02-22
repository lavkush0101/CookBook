package com.example.cookbook.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.adapter.DishesAdapter
import com.example.cookbook.modal.DishesModal
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Recipes : AppCompatActivity() {
    private lateinit var mRecylerviewRecpeise:RecyclerView
    private lateinit var dishesAdapter: DishesAdapter
    private lateinit var search_input:EditText
    private lateinit var recipeImageUpload:ImageView
    private lateinit var addDeatails_Relative:RelativeLayout
    private lateinit var database: DatabaseReference
    private var list = arrayListOf<DishesModal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_recipes)
        mRecylerviewRecpeise =findViewById(R.id.recylerview_recipes);
        search_input =findViewById(R.id.search_input);
        addDeatails_Relative =findViewById(R.id.addDeatailsRelative);
        readRecipeFirebaseList();

        search_input.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var inputSearchDishes: String = search_input.text.toString().trim()
//                if(dishModalList.get(0).filter { it.name.contains("n") }.toString())
//                if (dishModalList.containsAll(filter))

            }

        })

        initRecyclerView();
    }
    private fun initRecyclerView(){
            }
    /*
    add the dishes of Android
     */
    private fun setAddData(){
        recipeImageUpload = findViewById(R.id.recipe_image_upload)
        val recipeName = findViewById(R.id.recipe_name) as EditText
        val recipesType = findViewById(R.id.recipes_type) as TextView
        val submitTypes = findViewById(R.id.submit_data) as Button
            submitTypes.setOnClickListener {
            saveRecipeInAndroid(recipeName.text.toString().trim(),recipesType.text.toString().trim(),"fd")
            recipeName.setText("")
            recipesType.setText("")

        }
        recipeImageUpload.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
//            val fileUri = data?.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            recipeImageUpload.setImageBitmap(imageBitmap)

            //You can get File object from intent
//            val file: File = ImagePicker.getFile(data)!!
            //You can also get File Path from intent
//            val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


    fun addDishes(view: View) {
        addDeatails_Relative.visibility=View.VISIBLE
        setAddData();
    }

    fun onBackRecipes(view: View) {
        onBackPressed()
    }

//     add the recipe

    private fun saveRecipeInAndroid(recipeName: String, recipesType: String, recipeImageUpload: String) {
        val disheshModal:DishesModal=DishesModal(
            recipeName,
            recipeImageUpload,
            recipesType,
             "",
             "",
            ""
            )
//         databaseReference.child(user.getUid()).push().setValue(markerInfo);
        database = Firebase.database.reference
        database.child("recipeList").push().setValue(disheshModal);

        addDeatails_Relative.visibility=View.INVISIBLE

    }


//  Read the recipe list


    private fun readRecipeFirebaseList(){
      val ref=FirebaseDatabase.getInstance().getReference("recipeList")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot!=null) {
                    for (h in dataSnapshot.children){
                        val  dishes:DishesModal?=h.getValue(DishesModal::class.java)
                        Log.d("firebaseRespone",dishes?.name.toString());
                        if (dishes != null) {
                            list.add(dishes)
                        }
                        mRecylerviewRecpeise.apply {
                            layoutManager=LinearLayoutManager(this@Recipes)
                            dishesAdapter= DishesAdapter(list)
                            adapter=dishesAdapter
                        }


                    }

//                    Log.d("firebaseRespone",dataSnapshot.children.toString());
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
            }
        }
    ref.addValueEventListener(postListener)

    }

    override fun onStart() {
        super.onStart()
        readRecipeFirebaseList();
    }
}