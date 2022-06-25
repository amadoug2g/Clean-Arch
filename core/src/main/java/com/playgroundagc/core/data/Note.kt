package com.playgroundagc.core.data

/**
 * Created by Amadou on 24/06/2022
 *
 * Purpose: Note Data Class
 *
 */

data class Note (
    val title: String,
    val content: String,
    val createTime: Long,
    val updateTime: Long,
    val id: Long = 0L
        )