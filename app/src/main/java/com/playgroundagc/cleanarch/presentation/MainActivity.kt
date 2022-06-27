package com.playgroundagc.cleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playgroundagc.cleanarch.R
import com.playgroundagc.cleanarch.framework.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}