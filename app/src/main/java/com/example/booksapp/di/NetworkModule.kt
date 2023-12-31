package com.example.booksapp.di

import com.example.booksapp.MainApplication
import com.example.booksapp.common.Constants
import com.example.booksapp.data.source.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder().apply {
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(Constants.BASE_URL)
        client(okHttpClient)
    }.build()

    @Provides
    @Singleton
    fun provideBookService(retrofit: Retrofit) = retrofit.create<BookService>()
}