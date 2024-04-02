package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_bookmark")
data class Bookmark(
    @PrimaryKey
    val id: Int
)
