package com.example.cookbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R

class ComentAdapter (var commentList: List<String>): RecyclerView.Adapter<ComentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.comment_string,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recipesComment?.text=commentList.get(position)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var recipesComment: TextView? = null

        init {
            recipesComment=view.findViewById(R.id.user_comment);
        }

    }

}