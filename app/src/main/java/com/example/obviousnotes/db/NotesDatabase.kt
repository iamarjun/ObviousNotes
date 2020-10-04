package com.example.obviousnotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.model.User

@Database(
    entities = [Note::class, User::class],
    exportSchema = false,
    version = 4
)
abstract class NotesDatabase : RoomDatabase() {

    abstract val dao: NotesDao

    abstract val userDao: UserDao

    companion object {
        private const val DB_NAME = "notes_db"
        private var instance: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase = instance ?: synchronized(this) {
            instance ?: synchronized(this) {
                getDatabaseInstance(context).also {
                    instance = it
                }
            }
        }

        private fun getDatabaseInstance(context: Context): NotesDatabase =
            Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
    }

}