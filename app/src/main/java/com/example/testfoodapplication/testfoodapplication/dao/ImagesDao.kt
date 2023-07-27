package com.example.testfoodapplication.testfoodapplication.dao

import androidx.room.*
import com.example.testfoodapplication.testfoodapplication.model.Image

@Dao
interface ImagesDao {

    @Query("SELECT byteImages FROM images")
    suspend fun getAll(): List<String>

    @Insert
    suspend fun insert(byte: Image)

    @Update
    suspend fun update(byte: Image)

    @Delete
    suspend fun delete(byte: Image)
}