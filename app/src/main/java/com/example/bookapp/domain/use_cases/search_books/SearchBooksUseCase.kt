package com.example.bookapp.domain.use_cases.search_books

import androidx.paging.PagingData
import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

class SearchBooksUseCase(
    private val repository: Repository
) {

    operator fun invoke(query: String): Flow<PagingData<Book>>{
        return repository.searchBooks(query = query)
    }

}