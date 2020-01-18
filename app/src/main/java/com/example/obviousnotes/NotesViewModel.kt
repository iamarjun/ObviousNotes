package com.example.obviousnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.obviousnotes.model.Note

class NotesViewModel : ViewModel() {

    private val list = ArrayList<Note>()

    val newNote by lazy { MutableLiveData<Note>() }
    val notesList by lazy { MutableLiveData<List<Note>>() }

    fun addNote(note: Note) {
        newNote.postValue(note)
        list.add(note)
        notesList.postValue(list.reversed())
    }

}