package com.playgroundagc.core.repository

import com.playgroundagc.core.data.Note

/**
 * Created by Amadou on 24/06/2022
 *
 * Purpose: Note Interface
 *
 */

interface NoteDataSource {
    suspend fun add(note: Note)
    suspend fun get(id: Long)
    suspend fun getAll()
    suspend fun remove(note: Note)
}