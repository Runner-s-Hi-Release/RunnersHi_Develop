package com.example.runnershi_develop.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun serializateBadge(badges: List<Boolean>): Int {
        var result = 0
        var index = 1
        for (badge in badges) {
            if (badge)
                result += index
            index *= 2
        }
        return result
    }

    @TypeConverter
    fun deserializationBadge(value: Int): List<Boolean> {
        val result: MutableList<Boolean> = mutableListOf()
        var count = value
        for (i in 0..11) {
            when (count % 2) {
                0 -> result.add(false)
                1 -> result.add(true)
            }
            count /= 2
        }
        return result
    }

}