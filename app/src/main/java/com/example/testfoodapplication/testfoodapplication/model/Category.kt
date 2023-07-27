package com.example.testfoodapplication.testfoodapplication.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testfoodapplication.R

data class Category(
    val id : Int,
     val stringResourceId: String,
    @DrawableRes val imageResourceId: Int
)

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val name : String,
    var price : Int,
    val category: Int,
    var count : Int = 0,
    @DrawableRes val image: Int
)
