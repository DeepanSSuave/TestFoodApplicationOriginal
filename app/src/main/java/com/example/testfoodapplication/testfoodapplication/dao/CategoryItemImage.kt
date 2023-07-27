package com.example.testfoodapplication.testfoodapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testfoodapplication.testfoodapplication.model.ByteArrayEntity

@Dao
interface ByteArrayDao {

    @Query("SELECT * FROM byte_array")
    suspend fun getAllByteArrays(): List<ByteArrayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertByteArray(byteArrayEntity: ByteArrayEntity)
}