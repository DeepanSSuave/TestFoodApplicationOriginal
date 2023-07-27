package com.example.testfoodapplication.testfoodapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "byteImages") val images: String
)

//@Entity(tableName = "cartOfItems")
//data class CartItems(
//    @PrimaryKey(autoGenerate = true) val id: Int?,
//    @ColumnInfo(name = "cartItems") val items : Food
//)

@Entity(tableName = "checkouts")
data class Checkouts(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "food") val itemId : Int,
    @ColumnInfo(name = "time") val time : String,
    @ColumnInfo(name = "totalPrice") val totalPrice : Int
)