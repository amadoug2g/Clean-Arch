package com.playgroundagc.core.data

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Amadou on 24/06/2022
 *
 * Purpose: Note Data Class
 *
 */

data class Note(
    var title: String,
    var content: String,
    var createTime: Long,
    var updateTime: Long,
    val id: Long = 0L,
    var wordCount: Int = 0
) {
    val noteUpdatedDate: String
        get() {
            val formattedDate = SimpleDateFormat("dd MMM y", Locale.FRANCE)
            val result = formattedDate.format(Date(updateTime))

            return ("Last updated: $result")
        }
}