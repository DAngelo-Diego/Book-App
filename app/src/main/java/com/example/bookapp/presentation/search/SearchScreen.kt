package com.example.bookapp.presentation.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun SearchScreen() {
    Scaffold(topBar = { SearchTopBar(
        text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onClosedClicked = {}
    ) }) {

    }
}