package com.example.testfoodapplication.testfoodapplication.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(private var categoryId: (Int) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ImagesHolder>() {

    private var categories: List<Category> = emptyList()
    var clickedPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryData(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    inner class ImagesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.CategoryItemTV)

        @RequiresApi(Build.VERSION_CODES.M)
        fun setCategory(category: Category) {
            textView.text = category.stringResourceId
            changeColor(clickedPosition)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun changeColor(position: Int) {
            if (position == adapterPosition) {
                itemView.categoryItemLayout.setBackgroundResource(R.drawable.drawable_category_change)
                itemView.CategoryItemTV.setTextColor(itemView.context.getColor(R.color.white))
            } else {
                itemView.categoryItemLayout.setBackgroundResource(R.drawable.back_white_oval)
                itemView.CategoryItemTV.setTextColor(itemView.context.getColor(R.color.black))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ImagesHolder(view)

    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.setCategory(categories[position])
        holder.itemView.setOnClickListener {
            if (clickedPosition == position) return@setOnClickListener

            categoryId(categories[position].id)
            clickedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = categories.size

}