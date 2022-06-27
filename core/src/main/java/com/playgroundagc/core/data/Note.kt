package com.playgroundagc.core.data

/**
 * Created by Amadou on 24/06/2022
 *
 * Purpose: Note Data Class
 *
 */

data class Note (
    var title: String,
    var content: String,
    var createTime: Long,
    var updateTime: Long,
    val id: Long = 0L
        )