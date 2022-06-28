package com.playgroundagc.cleanarch.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.playgroundagc.cleanarch.framework.di.ApplicationModule
import com.playgroundagc.cleanarch.framework.di.DaggerViewModelComponent
import com.playgroundagc.core.data.Note
import com.playgroundagc.core.repository.NoteRepository
import com.playgroundagc.core.usecase.AddNote
import com.playgroundagc.core.usecase.GetAllNotes
import com.playgroundagc.core.usecase.GetNote
import com.playgroundagc.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Amadou on 26/06/2022, 01:18
 *
 * Note Fragment ViewModel
 *
 */

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
        }
    }
}