package com.keyinc.keymono.di

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.data.api.ClassroomApi
import com.keyinc.keymono.data.api.RequestApi
import com.keyinc.keymono.data.repository.AccountRepositoryImpl
import com.keyinc.keymono.data.repository.ClassroomRepositoryImpl
import com.keyinc.keymono.data.repository.RequestRepositoryImpl
import com.keyinc.keymono.domain.repository.AccountRepository
import com.keyinc.keymono.domain.repository.ClassroomRepository
import com.keyinc.keymono.domain.repository.RequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAuthRepository(tokenStorage: TokenStorage, accountApi: AccountApi): AccountRepository {
        return AccountRepositoryImpl(tokenStorage, accountApi)
    }

    @Provides
    @Singleton
    fun provideClassroomRepository(classroomApi: ClassroomApi): ClassroomRepository {
        return ClassroomRepositoryImpl(classroomApi)
    }

    @Provides
    @Singleton
    fun provideRequestRepository(tokenStorage: TokenStorage, requestApi: RequestApi): RequestRepository {
        return RequestRepositoryImpl(tokenStorage, requestApi)
    }
}