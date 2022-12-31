package com.example.bookapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.presentation.components.RatingWidget
import com.example.bookapp.ui.theme.LARGE_PADDING

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allBooks =homeViewModel.getAllBooks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ) {
        RatingWidget(modifier = Modifier.padding(all = LARGE_PADDING), rating = 3.5)
    }

}