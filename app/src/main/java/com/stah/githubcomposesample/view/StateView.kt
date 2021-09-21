package com.stah.githubcomposesample.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.stah.githubcomposesample.model.repository.User

@Composable
fun InitialView() {
    Text(text = "検索して下さい")
}

@Composable
fun LoadingView() {
    Text(text = "読込中")
}

@Composable
fun UserDetailView(user: User) {
    Column {
        Text(text = user.userId.value.toString())
        Text(text = user.name)
        Text(text = user.avatarImage.url.value)
        Text(text = user.blogUrl.value)
    }
}

@Composable
fun ErrorView() {
    Text(text = "読み込み失敗")
}
