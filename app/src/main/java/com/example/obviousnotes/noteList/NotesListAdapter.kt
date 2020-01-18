package com.example.obviousnotes.noteList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.obviousnotes.R
import com.example.obviousnotes.model.Note
import kotlinx.android.synthetic.main.layout_note_item.view.*

class NotesListAdapter(private val listener: OnNoteClickListener) : RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    interface OnNoteClickListener {
        fun onNoteClick(note: Note)
    }

    private var notesList: ArrayList<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_note_item, parent, false)
        return NotesListViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {

        val note = notesList[position]

        holder.title.text = note.title
        holder.content.text = note.content

        holder.view.setOnClickListener {
            listener.onNoteClick(note)
        }
    }

    fun addNotes(notes: List<Note>) {
        notesList.addAll(notes)
    }

    inner class NotesListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val title = view.title
        val content = view.content
    }

}