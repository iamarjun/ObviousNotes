package com.example.obviousnotes.di

import android.content.Context
import com.example.obviousnotes.db.NotesDao
import com.example.obviousnotes.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase =
        NotesDatabase.getInstance(context)

    @Provides
    fun provideDao(database: NotesDatabase): NotesDao = database.dao
}