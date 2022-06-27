package com.playgroundagc.cleanarch.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.playgroundagc.core.data.Note

/**
 * Created by Amadou on 26/06/2022, 00:20
 *
 * Room DB Entity
 */

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,

    @ColumnInfo(name = "create_date")
    val createTime: Long,

    @ColumnInfo(name = "update_date")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(note.title, note.content, note.createTime, note.updateTime)
    }

    fun toNote() = Note(title, content, createTime, updateTime, id)
}
