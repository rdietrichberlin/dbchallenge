package com.deutschebahn.challange.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailScreen(
    onBackClick: () -> Unit,
    detailViewModel: MovieDetailViewModel
) {
    when (val state = detailViewModel.state.collectAsState().value) {
        is MovieDetailViewModel.State.Data -> {
            Column(modifier = Modifier.fillMaxSize()) {
                MovieDetailTopAppBar(title = state.title, onBackClick = {
                    onBackClick()
                })

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(state.vote, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(state.overview)
                    Text(state.poster) // TBD resolve imiage uri
                }
            }
        }
    }
}

@Composable
fun MovieDetailTopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(title, textAlign = TextAlign.Center)
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier,
                    contentDescription = null
                )
            }
        }
    )
}