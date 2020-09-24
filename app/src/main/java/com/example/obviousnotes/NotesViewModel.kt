package com.example.obviousnotes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.obviousnotes.db.NotesDao
import com.example.obviousnotes.model.Note
import kotlinx.coroutines.launch
import timber.log.Timber

class NotesViewModel @ViewModelInject constructor(private val dao: NotesDao) : ViewModel() {

    val notesList: LiveData<List<Note>>
        get() = liveData {
            val notes = dao.getAllNotes()
            Timber.d(notes.toString())
            emit(notes)
        }

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