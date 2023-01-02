package com.example.bookapp.presentation.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery

    Scaffold(topBar = { SearchTopBar(
        text = searchQuery,
        onTextChanged = {
                        searchViewModel.updateSearchQuery(query = it)
        },
        onSearchClicked = {},
        onClosedClicked = {
            navController.popBackStack()
        }
    ) }) {

    }
}