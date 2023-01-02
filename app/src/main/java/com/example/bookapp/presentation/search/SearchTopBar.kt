package com.example.bookapp.presentation.search


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookapp.R
import com.example.bookapp.ui.theme.*


@Composable
fun SearchTopBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onClosedClicked: () -> Unit
) {
    SearchWidget(text = text, onTextChanged = onTextChanged, onSearchClicked = onSearchClicked, onClosedClicked = onClosedClicked )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onClosedClicked: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APPBAR_HEIGHT),
        color = MaterialTheme.colors.topBarSearch,
        elevation = 6.dp
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = "Search your topic...",
                    color = MaterialTheme.colors.topBarText
                )
            },
            textStyle = TextStyle(
                color =  MaterialTheme.colors.topBarText
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.alpha(alpha = 0.4f),
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search__icon),
                        tint = MaterialTheme.colors.topBarText
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChanged("")
                        } else {
                            onClosedClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colors.topBarText
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.contentBtnText,
                focusedIndicatorColor = MaterialTheme.colors.topBarText.copy(alpha = 0.4f),
                unfocusedIndicatorColor = MaterialTheme.colors.topBarText.copy(alpha = 0.2f)
            )
        )
    }
}

@Preview
@Composable
fun Prev() {
    SearchWidget(text = "Search your topic...", onTextChanged = {}, onSearchClicked = {}, onClosedClicked = {})
}