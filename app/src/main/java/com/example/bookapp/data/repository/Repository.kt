package com.example.bookapp.data.repository

import androidx.paging.PagingData
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.repository.DataStoreOperations
import com.example.bookapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllBooks(): Flow<PagingData<Book>>{
        return remote.getAllBooks()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}