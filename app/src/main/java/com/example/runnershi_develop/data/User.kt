package com.example.runnershi_develop.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey val user_Idx: Int,
    val nickname: String,
    val gender: Int,
    val level: Int,
    val image: Int,
    @SerializedName("badge")
    val badges: List<Boolean>,
    val win: Int,
    val lose: Int
)
