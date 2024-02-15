package com.keyinc.keymono.di

import android.content.Context
import com.keyinc.keymono.data.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataModule  {

    @Provides
    fun provideTokenStorage(@ApplicationContext context: Context) : TokenStorage {
        return TokenStorage(context = context)
    }

}