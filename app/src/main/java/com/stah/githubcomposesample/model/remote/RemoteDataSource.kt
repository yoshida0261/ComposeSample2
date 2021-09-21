package com.stah.githubcomposesample.model.remote

import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getGitHubUser(userName: String): GithubUser
}

class RemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient,
) : RemoteDataSource {
    override suspend fun getGitHubUser(userName: String): GithubUser {
        val response = apiClient.getGitHubUser(userName = userName)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw HttpException()
    }
}

class HttpException : Throwable()
