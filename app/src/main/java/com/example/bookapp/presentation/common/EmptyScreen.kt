package com.example.bookapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.example.bookapp.R
import com.example.bookapp.ui.theme.DarkGray
import com.example.bookapp.ui.theme.LightGray
import com.example.bookapp.ui.theme.NETWORK_ERROR_HEIGHT
import com.example.bookapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    val message by remember {
        mutableStateOf("Find the topic you want!")
    }
    val icon by remember {
        mutableStateOf(
            when (message) {
                "Server Unavailable." -> {
                    R.drawable.serverunavailable
                }
                "Not Internet" -> {
                    R.drawable.notinternet
                }
                else -> {  R.drawable.search_screen
                }
            }
        )
    }

    /*if (error != null){
        message = parseErrorMessage(error = error)
        icon = R.drawable.serverunavailable
    }*/

    var starAnimation by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (starAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        starAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, icon = icon, message = message)
}

@Composable
fun EmptyContent(alphaAnim: Float, icon: Int, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    //Log.d("parseErrorMessage", message)
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Not Internet"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreviewInternetError() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.notinternet,
        message = "Internet Unavailable."
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreviewInternetErrorDark() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.notinternet,
        message = "Internet Unavailable."
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreviewServerError() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.serverunavailable,
        message = "Server Unavailable."
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreviewServerErrorDark() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.serverunavailable,
        message = "Server Unavailable."
    )
}