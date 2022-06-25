package com.playgroundagc.cleanarch.framework

import android.content.Context
import com.playgroundagc.cleanarch.framework.db.DatabaseService
import com.playgroundagc.cleanarch.framework.db.NoteEntity
import com.playgroundagc.core.data.Note
import com.playgroundagc.core.repository.NoteDataSource

/**
 * Created by Amadou on 26/06/2022, 00:54
 *
 * Room Data Source Implementation
 */

class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNotesEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}