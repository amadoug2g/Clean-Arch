package com.playgroundagc.cleanarch.framework.di

import com.playgroundagc.cleanarch.framework.ListViewModel
import com.playgroundagc.cleanarch.framework.NoteViewModel
import dagger.Component

/**
 * Created by Amadou on 28/06/2022, 21:24
 */

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}