package com.playgroundagc.core.usecase

import com.playgroundagc.core.data.Note

/**
 * Created by Amadou on 28/06/2022, 21:38
 *
 * Get Word Count USE CASE
 *
 */

class GetWordCount {
    operator fun invoke(note: Note) = getCount(note.title) + getCount(note.content)

    private fun getCount(content: String) =
        content
            .split(" ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
}