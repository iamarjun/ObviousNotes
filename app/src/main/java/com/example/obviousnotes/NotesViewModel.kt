package com.example.obviousnotes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obviousnotes.db.NotesDao
import com.example.obviousnotes.model.Note
import kotlinx.coroutines.launch

class NotesViewModel @ViewModelInject constructor(private val dao: NotesDao) : ViewModel() {

    val notesList: LiveData<List<Note>>
        get() = dao.getAllNotes()

    fun addNote(note: Note) {
        viewModelScope.launch {
            dao.insert(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            dao.update(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.delete(note)
        }
    }

}