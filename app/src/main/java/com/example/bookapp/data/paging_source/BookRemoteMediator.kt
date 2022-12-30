package com.example.bookapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.BookRemoteKeys

@ExperimentalPagingApi
class BookRemoteMediator(
    private val bookApi: BookApi, private val bookDatabase: BookDatabase
) : RemoteMediator<Int, Book>() {


    private val bookDao = bookDatabase.bookDao()
    private val bookRemoteKeysDao = bookDatabase.bookRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Book>): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached =  remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!= null)
                    nextPage
                }
            }

            val response = bookApi.getAllBooks(page = page)
            if (response.books.isNotEmpty()) {
                bookDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        bookDao.deleteAllBooks()
                        bookRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.books.map { book ->
                        BookRemoteKeys(
                            id = book.id, prevPage = prevPage, nextPage = nextPage
                        )
                    }
                    bookRemoteKeysDao.addAllRemoteKeys(bookRemoteKeys = keys)
                    bookDao.addBooks(books = response.books)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Book>)
    : BookRemoteKeys? {
        return state.pages.lastOrNull {it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { book ->
                bookRemoteKeysDao.getRemoteKeys(bookId = book.id)
            }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Book>)
    : BookRemoteKeys? {
        return state.anchorPosition?.let {
                position -> state.closestItemToPosition(position)?.id?.let { id
            -> bookRemoteKeysDao.getRemoteKeys(bookId = id) } }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Book>)
            :BookRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { book ->
                bookRemoteKeysDao.getRemoteKeys(bookId = book.id)
            }
    }
}