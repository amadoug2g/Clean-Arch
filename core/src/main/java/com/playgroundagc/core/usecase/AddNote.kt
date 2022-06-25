package com.playgroundagc.core.usecase

import com.playgroundagc.core.data.Note
import com.playgroundagc.core.repository.NoteRepository

/**
 * Created by Amadou on 24/06/2022, 15:21
 *
 * Purpose: Add Note USE CASE
 *
 */

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}