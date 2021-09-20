package com.stah.githubcomposesample.model.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RemoteDataSourceModule {

    @Provides
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource {
        return remoteDataSource
    }

}
