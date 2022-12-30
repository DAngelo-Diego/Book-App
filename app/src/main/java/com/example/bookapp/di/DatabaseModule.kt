package com.example.bookapp.di

import android.content.Context
import androidx.room.Room
import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.util.Constants.BOOK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BookDatabase::class.java,
        BOOK_DATABASE
    ).build()

}