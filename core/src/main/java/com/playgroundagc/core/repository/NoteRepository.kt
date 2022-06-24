package com.playgroundagc.core.repository

import com.playgroundagc.core.data.Note

/**
 * Created by Amadou on 24/06/2022, 15:17
 *
 * Purpose: Note Repository
 *
 */

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.add(note)
    suspend fun getNote(id: Long) = dataSource.get(id)
    suspend fun getAllNotes() = dataSource.getAll()
    suspend fun removeNote(note: Note) = dataSource.remove(note)
}