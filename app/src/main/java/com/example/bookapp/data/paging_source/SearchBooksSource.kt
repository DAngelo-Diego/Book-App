package com.example.bookapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.domain.model.Book
import javax.inject.Inject

class SearchBooksSource@Inject constructor(
    private val bookApi: BookApi,
    private val query: String
): PagingSource<Int,Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val apiResponse = bookApi.searchBooks(name = query)
            val books = apiResponse.books
            if (books.isNotEmpty()){
                LoadResult.Page(
                    data = books,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage,
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition
    }
}