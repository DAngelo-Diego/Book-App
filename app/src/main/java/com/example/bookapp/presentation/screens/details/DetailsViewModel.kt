package com.example.bookapp.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.use_cases.UseCases
import com.example.bookapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedBook: MutableStateFlow<Book?> = MutableStateFlow(null)
    val selectedBook: StateFlow<Book?> = _selectedBook

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val bookId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedBook.value = bookId?.let { useCases.getSelectedBookUseCase(bookId= bookId) }
            //_selectedBook.value?.name?.let { Log.d("Book", it) } (log for see if the id is passing correctly)
        }
    }

}