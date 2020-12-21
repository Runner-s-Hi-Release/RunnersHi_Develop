package com.example.runnershi_develop.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Opponent(
    val run_idx: Int,
    val opponent_level: Int,
    val opponent_nickname: String,
    val opponent_image: Int,
    val opponent_win: Int,
    val opponent_lose: Int
): Parcelable