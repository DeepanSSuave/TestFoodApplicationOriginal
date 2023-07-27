package com.example.testfoodapplication.testfoodapplication.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.testfoodapplication.testfoodapplication.model.Food


@Dao
interface CartOfItemsDao {

    @Query("SELECT * FROM food")
    suspend fun getAll(): List<Food>

    @Insert
    suspend fun insert(item: Food)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(item: List<Food>)

    @Update
    suspend fun update(item: Food)

    @Delete
    suspend fun delete(item: Food)

    @Query("DELETE FROM food")
    suspend fun deleteTable()
}