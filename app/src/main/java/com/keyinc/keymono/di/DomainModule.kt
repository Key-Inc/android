package com.keyinc.keymono.di

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.data.api.ClassroomApi
import com.keyinc.keymono.data.api.KeyApi
import com.keyinc.keymono.data.api.RequestApi
import com.keyinc.keymono.data.api.UserApi
import com.keyinc.keymono.data.repository.AccountRepositoryImpl
import com.keyinc.keymono.data.repository.ClassroomRepositoryImpl
import com.keyinc.keymono.data.repository.KeyRepositoryImpl
import com.keyinc.keymono.data.repository.RequestRepositoryImpl
import com.keyinc.keymono.domain.repository.AccountRepository
import com.keyinc.keymono.domain.repository.ClassroomRepository
import com.keyinc.keymono.domain.repository.KeyRepository
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
    fun provideAuthRepository(
        tokenStorage: TokenStorage,
        accountApi: AccountApi
    ): AccountRepository {
        return AccountRepositoryImpl(tokenStorage, accountApi)
    }

    @Provides
    @Singleton
    fun provideClassroomRepository(classroomApi: ClassroomApi): ClassroomRepository {
        return ClassroomRepositoryImpl(classroomApi)
    }

    @Provides
    @Singleton
    fun provideKeyRepository(
        tokenStorage: TokenStorage,
        keyApi: KeyApi,
        userApi: UserApi
    ): KeyRepository {
        return KeyRepositoryImpl(tokenStorage, keyApi, userApi)
    }

    @Provides
    @Singleton
    fun provideRequestRepository(
        tokenStorage: TokenStorage,
        requestApi: RequestApi
    ): RequestRepository {
        return RequestRepositoryImpl(tokenStorage, requestApi)
    }


}