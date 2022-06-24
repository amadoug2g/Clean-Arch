package com.playgroundagc.core.usecase

import com.playgroundagc.core.repository.NoteRepository

/**
 * Created by Amadou on 24/06/2022, 15:21
 *
 * Purpose: Get All Notes USE CASE
 *
 */

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}