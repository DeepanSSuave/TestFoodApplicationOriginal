package com.example.testfoodapplication.testfoodapplication.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testfoodapplication.R
import com.example.testfoodapplication.databinding.FoodItemBinding
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.viewModel.MainActivityViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.food_item.*
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter(private var foodList: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private var wishlist: Boolean = false
    private var listener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFoodData(foods: List<Food>) {
        foodList = foods
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(position)
                }
            }
        }

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun setFood(food: Food) {
            binding.apply {
                CategoryItemTV.text = food.name
                CategoryItemIV.setImageResource(food.image)

                if (wishlist) {
                    wishListIcon.setImageResource(R.drawable.ic_round_favorite_24)
                }

                wishListIcon.setOnClickListener {
                    val currentImageResource = it.wishListIcon.drawable.constantState?.newDrawable()

                    if (currentImageResource?.constantState == it.resources.getDrawable(
                            R.drawable.ic_round_favorite_border_24,
                            null
                        ).constantState
                    ) {
                        it.wishListIcon.setImageResource(R.drawable.ic_round_favorite_24)
                        wishlist = true
                    } else if (currentImageResource?.constantState == it.resources.getDrawable(
                            R.drawable.ic_round_favorite_24,
                            null
                        ).constantState
                    ) {
                        it.wishListIcon.setImageResource(R.drawable.ic_round_favorite_border_24)
                        wishlist = false
                    } else {
                        it.wishListIcon.setImageResource(R.drawable.ic_round_favorite_border_24)
                        wishlist = false
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setFood(foodList[position])

        val color = when (position % 3) {
            0 -> R.color.cardColor1
            1 -> R.color.cardColor2
            else -> R.color.cardColor3
        }

        val context = holder.itemView.context

        val drawable = ContextCompat.getDrawable(context, R.drawable.tool_item_bg)
        drawable?.setTint(ContextCompat.getColor(context, color))
        holder.itemView.background = drawable
    }

    override fun getItemCount(): Int = foodList.size
}