package com.example.runnershi_develop.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Badge(
    val badge: Boolean,
    val index: Int
) : Parcelable