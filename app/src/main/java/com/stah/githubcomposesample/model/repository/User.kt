package com.stah.githubcomposesample.model.repository


data class User(
    val userId: UserId,
    val name: String,
    val avatarImage: NetworkImage,
    val blogUrl: Url
)

data class UserId(val value: Long)
data class NetworkImage(val url: Url)
data class Url(val value: String)
