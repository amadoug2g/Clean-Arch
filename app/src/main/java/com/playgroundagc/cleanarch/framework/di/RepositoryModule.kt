package com.playgroundagc.cleanarch.framework.di

import android.app.Application
import com.playgroundagc.cleanarch.framework.RoomNoteDataSource
import com.playgroundagc.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Amadou on 28/06/2022, 21:21
 */

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}