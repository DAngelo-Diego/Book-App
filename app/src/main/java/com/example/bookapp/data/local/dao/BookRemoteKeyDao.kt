package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.BookRemoteKey

@Dao
interface BookRemoteKeyDao {

    @Query("SELECT * FROM book_remote_key_table WHERE id = :id")
    suspend fun getRemoteKey(id: Int): BookRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(bookRemoteKeys: List<BookRemoteKey>)

    @Query("DELETE FROM book_remote_key_table")
    suspend fun deleteAllRemoteKeys()

}