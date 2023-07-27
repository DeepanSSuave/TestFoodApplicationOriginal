package com.example.testfoodapplication.testfoodapplication.dao

import androidx.room.*
import com.example.testfoodapplication.testfoodapplication.model.Checkouts


@Dao
interface TransactionDao {
    @Query("SELECT * FROM checkouts")
    suspend fun getAll(): List<Checkouts>

    @Insert
    suspend fun insert(item: Checkouts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<Checkouts>)

    @Update
    suspend fun update(item: Checkouts)

    @Delete
    suspend fun delete(item: Checkouts)
}