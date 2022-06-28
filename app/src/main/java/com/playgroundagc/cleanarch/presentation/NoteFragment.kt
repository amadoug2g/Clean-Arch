package com.playgroundagc.cleanarch.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_note -> deleteWindow()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteWindow(): Boolean {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete note")
            .setMessage("Would you like to delete this note?")
            .setPositiveButton(R.string.positive_answer) { _: DialogInterface, _: Int ->
                deleteNote(currentNote)
            }
            .setNegativeButton(R.string.negative_answer) { _: DialogInterface, _: Int ->
            }.show()
        return true
    }

    private fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
        requireActivity().onBackPressed()
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