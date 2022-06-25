package com.playgroundagc.cleanarch.framework

import com.playgroundagc.core.usecase.AddNote
import com.playgroundagc.core.usecase.GetAllNotes
import com.playgroundagc.core.usecase.GetNote
import com.playgroundagc.core.usecase.RemoveNote

/**
 * Created by Amadou on 26/06/2022, 01:09
 */

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)
