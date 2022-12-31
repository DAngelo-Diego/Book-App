package com.example.bookapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.data.paging_source.BookRemoteMediator
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.RemoteDataSource
import com.example.bookapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
     private val bookApi: BookApi,
     private val bookDatabase: BookDatabase
): RemoteDataSource {
    
    private val bookDao = bookDatabase.bookDao()
    
    override fun getAllBooks(): Flow<PagingData<Book>> {
        val pagingSourceFactory = { bookDao.getAllBooks() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = BookRemoteMediator(
                bookApi = bookApi,
                bookDatabase = bookDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchBooks(): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }
}