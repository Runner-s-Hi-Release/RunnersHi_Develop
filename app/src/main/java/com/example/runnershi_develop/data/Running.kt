package com.example.runnershi_develop.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Running(
    val date: String,
    val distance: Int,
    val time: String,
    val runIdx: Int,
    val gameIdx: Int,
    val result: Int
) : Parcelable