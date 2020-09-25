package com.example.runnershi_develop.data
import android.service.notification.NotificationListenerService
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

sealed class RankingRunner{
    data class WinOrLoseRunner(
        @Json(name = "nickname")
        val nickname: String,
        @Json(name = "image")
        val image: Int,
        @Json(name = "user_idx")
        val user_idx: Int,
        @Json(name = "win")
        val win: Int,
        @Json(name = "lose")
        val lose: Int
    ):RankingRunner()

    data class HeavyRunner(
        @Json(name = "nickname")
        val nickname: String,
        @Json(name = "image")
        val image: Int,
        @Json(name = "user_idx")
        val user_idx: Int,
        @Json(name = "distance_sum")
        val distance_sum: Int
    ):RankingRunner()
}

