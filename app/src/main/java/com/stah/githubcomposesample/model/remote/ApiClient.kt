package com.stah.githubcomposesample.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("user/{username}")
    suspend fun getGitHubUser(@Path("username") userName: String): Response<GithubUser>
}
