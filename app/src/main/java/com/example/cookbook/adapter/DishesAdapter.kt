package com.example.cookbook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookbook.modal.DishesModal
import com.example.cookbook.R
import com.example.cookbook.Activity.RecipesDetails

class DishesAdapter(var items: List<DishesModal>): RecyclerView.Adapter<DishesAdapter.ViewHolder>() {
    private lateinit var mContext :Context
 
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item,parent,false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dishesName?.text=items.get(position).name
        holder.dishesImage?.setImageResource(R.drawable.ic_launcher_background)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RecipesDetails::class.java)
            //listener?.onClick(AlbumsData)
            intent.putExtra("dd", "ff")
            holder.itemView.context.startActivity(intent)
        }

        try {
            holder.dishesImage?.let {
                Glide.with(mContext)
                    .load("")
                    .into(it)
            }
        }catch (e: Exception) {
            e.localizedMessage
        }

    }

    override fun getItemCount(): Int {
       return items.size
    }
    inner class ViewHolder(row: View):RecyclerView.ViewHolder(row){
        var dishesImage: ImageView? = null
        var dishesName: TextView? = null

        init {
            this.dishesImage = row.findViewById(R.id.dishes_image)
            this.dishesName = row.findViewById(R.id.dishes_name)
        }


    }
}
