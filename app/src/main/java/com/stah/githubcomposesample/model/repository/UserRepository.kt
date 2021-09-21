package com.stah.githubcomposesample.model.repository

import com.stah.githubcomposesample.model.remote.GithubUser
import com.stah.githubcomposesample.model.remote.RemoteDataSource
import javax.inject.Inject

interface UserRepository {
    suspend fun getUser(userName: String): User
}

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {
    override suspend fun getUser(userName: String): User {
        return remoteDataSource.getGitHubUser(userName = userName).toUser()
    }
}

private fun GithubUser.toUser(): User {
    return User(
        userId = UserId(value = id),
        name = name,
        avatarImage = NetworkImage(url = Url(value = avatarUrl)),
        blogUrl = Url(value = blog)
    )
}
