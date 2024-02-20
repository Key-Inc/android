package com.keyinc.keymono.di

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.data.repository.AccountRepositoryImpl
import com.keyinc.keymono.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideAuthRepository(tokenStorage: TokenStorage, accountApi: AccountApi): AccountRepository {
        return AccountRepositoryImpl(tokenStorage, accountApi)
    }
}