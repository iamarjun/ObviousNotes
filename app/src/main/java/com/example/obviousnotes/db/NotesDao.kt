package com.example.obviousnotes.db

import androidx.room.*
import com.example.obviousnotes.model.Note

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>
}