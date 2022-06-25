package com.playgroundagc.cleanarch.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * Created by Amadou on 26/06/2022, 00:28
 *
 * Room Note DAO
 */

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    suspend fun getAllNotesEntities(): List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}

/*
*
* companion object {

    private const val DATABASE_NAME = "note.db"

    private var instance: DatabaseService? = null

    private fun create(context: Context): DatabaseService =
        Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    fun getInstance(context: Context): DatabaseService =
        (instance ?: create(context)).also { instance = it }
}
*
* */