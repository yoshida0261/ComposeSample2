package com.stah.githubcomposesample.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.stah.githubcomposesample.model.repository.User
import com.stah.githubcomposesample.viewmodel.MainViewModel

@Composable
fun MainView(mainViewModel: MainViewModel) {
    val uiState: MainViewModel.UiState by mainViewModel.uiState
    Column(Modifier.fillMaxWidth()) {
        SearchView(
            searchQuery = mainViewModel.searchQuery,
            onSearchButtonTapped = { mainViewModel.onSearchTapped() }
        )
        when (uiState) {
            is MainViewModel.UiState.Initial -> {
                InitialView()
            }
            is MainViewModel.UiState.Loading -> {
                LoadingView()
            }
            is MainViewModel.UiState.Success -> {
                UserDetailView(user = uiState.requireUser())
            }
            is MainViewModel.UiState.Failure -> {
                ErrorView()
            }
        }
    }
}

private fun MainViewModel.UiState.requireUser(): User {
    if (this !is MainViewModel.UiState.Success) throw IllegalStateException("user is not loading")
    return user
}

@Composable
fun SearchView(
    searchQuery: MutableState<String>,
    onSearchButtonTapped: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        TextField(label = {
            Text("Githubのアカウントを入力")
        },
            value = searchQuery.value,
            onValueChange = { text ->
                searchQuery.value = text
            },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                onSearchButtonTapped()
            }
        ){
            Text(text = "検索")
        }
    }
}
