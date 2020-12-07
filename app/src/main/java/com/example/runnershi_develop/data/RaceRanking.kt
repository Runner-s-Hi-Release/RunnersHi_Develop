package com.example.runnershi_develop.data

import com.squareup.moshi.Json

data class RaceRanking(
    @Json(name = "ranking")
    val ranking: Int
)