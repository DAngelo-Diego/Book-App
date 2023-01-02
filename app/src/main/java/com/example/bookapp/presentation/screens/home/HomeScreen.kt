package com.example.bookapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.ListContent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val allBooks =homeViewModel.getAllBooks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ListContent(books = allBooks, navController = navController)
        }
    )
}