package com.playgroundagc.core.usecase

import com.playgroundagc.core.repository.NoteRepository

/**
 * Created by Amadou on 24/06/2022, 15:22
 *
 * Purpose: Get Note USE CASE
 *
 */

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}