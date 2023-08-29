package com.example.booksapp.di

import com.example.booksapp.data.repository.BookRepository
import com.example.booksapp.data.source.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesBookRepository(bookService: BookService): BookRepository =
        BookRepository(bookService)


}