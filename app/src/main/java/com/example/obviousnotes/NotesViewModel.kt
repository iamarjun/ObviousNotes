package com.example.obviousnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.obviousnotes.model.Note

class NotesViewModel : ViewModel() {

    private val list = ArrayList<Note>()

    val newNote: LiveData<Note>
        get() = _newNote
    val notesList: LiveData<List<Note>>
        get() = _notesList

    private val _newNote by lazy { MutableLiveData<Note>() }
    private val _notesList by lazy { MutableLiveData<List<Note>>() }

    fun addNote(note: Note) {
        _newNote.value = (note)
        list.add(note)
        _notesList.value = (list.reversed())
    }

}