package com.playgroundagc.cleanarch.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.playgroundagc.cleanarch.framework.RoomNoteDataSource
import com.playgroundagc.cleanarch.framework.UseCases
import com.playgroundagc.cleanarch.framework.di.ApplicationModule
import com.playgroundagc.cleanarch.framework.di.DaggerViewModelComponent
import com.playgroundagc.core.data.Note
import com.playgroundagc.core.repository.NoteRepository
import com.playgroundagc.core.usecase.AddNote
import com.playgroundagc.core.usecase.GetAllNotes
import com.playgroundagc.core.usecase.GetNote
import com.playgroundagc.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Amadou on 27/06/2022, 20:40
 *
 * List Fragment ViewModel
 *
 */

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val list = useCases.getAllNotes()
            notes.postValue(list)
        }
    }
}