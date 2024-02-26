package com.keyinc.keymono.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.data.api.ClassroomApi
import com.keyinc.keymono.data.api.NetworkConstants
import com.keyinc.keymono.data.api.NetworkConstants.CONNECT_TIMEOUT
import com.keyinc.keymono.data.api.NetworkConstants.READ_TIMEOUT
import com.keyinc.keymono.data.api.NetworkConstants.WRITE_TIMEOUT
import com.keyinc.keymono.data.api.RequestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val gson: Gson = GsonBuilder().create()

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage {
        return TokenStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    fun provideClassroomApi(retrofit: Retrofit): ClassroomApi {
        return retrofit.create(ClassroomApi::class.java)
    }

    @Provides
    fun provideRequestApi(retrofit: Retrofit): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }

}