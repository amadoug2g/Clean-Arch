package com.playgroundagc.cleanarch.framework

import com.playgroundagc.core.usecase.*

/**
 * Created by Amadou on 26/06/2022, 01:09
 */

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)
