package com.playgroundagc.cleanarch.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.playgroundagc.cleanarch.databinding.SingleNoteBinding
import com.playgroundagc.core.data.Note

/**
 * Created by Amadou on 27/06/2022, 19:15
 *
 * Note Adapter
 *
 */

class NotesListAdapter(private var notesList: List<Note>, private val action: ListAction) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = SingleNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int = notesList.size

    fun updateNotes(newNotesList: List<Note>) {
        val diffUtil = MyDiffUtil(notesList, newNotesList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        notesList = newNotesList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NoteViewHolder(private val binding: SingleNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                this.note = note

                this.cardView.setOnClickListener { action.onClick(note.id) }

                this.wordCount.text = "Words: ${note.wordCount}"
            }
        }
    }
}

class MyDiffUtil(private val oldList: List<Note>, private val newList: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.count()

    override fun getNewListSize(): Int = newList.count()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldList[oldItemPosition] == newList[newItemPosition])
}