package com.playgroundagc.cleanarch.framework.di

import com.playgroundagc.cleanarch.framework.UseCases
import com.playgroundagc.core.repository.NoteRepository
import com.playgroundagc.core.usecase.*
import dagger.Module
import dagger.Provides

/**
 * Created by Amadou on 28/06/2022, 21:23
 */

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount(),
    )
}