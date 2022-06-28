package com.playgroundagc.cleanarch.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.playgroundagc.cleanarch.R
import com.playgroundagc.cleanarch.databinding.FragmentNoteBinding
import com.playgroundagc.cleanarch.framework.NoteViewModel
import com.playgroundagc.core.data.Note

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    private val viewModel: NoteViewModel by activityViewModels()

    private var noteId = 0L

    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        binding.saveNoteFAB.setOnClickListener {
            if (checkIfEmpty()) {
                saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this) { value ->
            if (value) {
                hideKeyboard()
                Navigation.findNavController(binding.titleView).popBackStack()
            } else {
                Toast.makeText(context, "An error occurred!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.currentNote.observe(this) { note ->
            note?.let {
                currentNote = it
                binding.note = it
            }
        }
    }

    private fun saveNote(note: Note) {
        val time = System.currentTimeMillis()
        note.apply {
            title = binding.titleView.text.toString()
            content = binding.contentView.text.toString()
            updateTime = time
            if (id == 0L) createTime = time
        }
        viewModel.saveNote(note)
    }

    private fun checkIfEmpty(): Boolean {
        return (binding.titleView.text.isNotEmpty() || binding.contentView.text.isNotEmpty())
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.titleView.windowToken, 0)
    }
}