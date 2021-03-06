package com.example.obviousnotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    var title: String,
    var content: String,
    var timeStamp: String
) : Parcelable