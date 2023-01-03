package com.example.bookapp.data.repository

import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(bookDatabase: BookDatabase): LocalDataSource {

    private val bookDao = bookDatabase.bookDao()

    override suspend fun getSelectedBook(bookId: Int): Book {
        return bookDao.getSelectedBook(bookId= bookId)
    }

}