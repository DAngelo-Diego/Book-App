package com.example.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapp.util.Constants.BOOK_DATABASE_TABLE

@Entity(tableName = BOOK_DATABASE_TABLE)
data class Book(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val month: String,
    val day: String,
    val tags: List<String>,
)
