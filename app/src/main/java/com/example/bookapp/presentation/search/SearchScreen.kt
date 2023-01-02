package com.example.bookapp.presentation.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.presentation.common.ListContent

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery

    val books = searchViewModel.searchedBooks.collectAsLazyPagingItems()

    Scaffold(topBar = {
        SearchTopBar(
            text = searchQuery,
            onTextChanged = {
                searchViewModel.updateSearchQuery(query = it)
            },
            onSearchClicked = {
                searchViewModel.searchBooks(query = it)
            },
            onClosedClicked = {
                navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(books = books, navController = navController)
        }
    )
}