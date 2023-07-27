package com.example.testfoodapplication.testfoodapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "byte_array")
data class ByteArrayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val images: ByteArray
)
