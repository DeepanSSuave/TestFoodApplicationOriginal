package com.example.testfoodapplication.testfoodapplication.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testfoodapplication.testfoodapplication.dao.ByteArrayDao
import com.example.testfoodapplication.testfoodapplication.dao.CartOfItemsDao
import com.example.testfoodapplication.testfoodapplication.dao.ImagesDao
import com.example.testfoodapplication.testfoodapplication.dao.TransactionDao
import com.example.testfoodapplication.testfoodapplication.model.*

@Database(entities = [Image::class, ByteArrayEntity::class, Food::class, Checkouts::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): ImagesDao
    abstract fun byteArrayDao(): ByteArrayDao
    abstract fun CartOfItemsDao(): CartOfItemsDao
    abstract fun transactionDao() : TransactionDao
}



