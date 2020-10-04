package com.example.obviousnotes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obviousnotes.db.NotesDao
import com.example.obviousnotes.db.UserDao
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.model.User
import com.example.obviousnotes.util.toUser
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.launch

class NotesViewModel @ViewModelInject constructor(
    private val dao: NotesDao,
    private val userDao: UserDao
) : ViewModel() {

    private lateinit var _currentUser: User

    val currentUser: User
        get() = _currentUser

    val notesList: LiveData<List<Note>>
        get() = dao.getAllNotes(_currentUser.id)

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

    fun addUser(it: GoogleSignInAccount) {
        _currentUser = it.toUser()
        viewModelScope.launch {
            userDao.insert(_currentUser)
        }
    }

}