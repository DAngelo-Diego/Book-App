package com.example.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapp.util.Constants.BOOK_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = BOOK_REMOTE_KEY_DATABASE_TABLE)
data class BookRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
