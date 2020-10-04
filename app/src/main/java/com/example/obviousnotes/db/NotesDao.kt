package com.example.obviousnotes.db

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM note WHERE userId = :userId")
    fun getAllNotes(userId: String): LiveData<List<Note>>
}