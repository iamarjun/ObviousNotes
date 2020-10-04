package com.example.obviousnotes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.obviousnotes.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user  WHERE id = :userId")
    fun getUser(userId: String): LiveData<User>
}