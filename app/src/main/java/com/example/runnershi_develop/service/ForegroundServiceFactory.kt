package com.example.runnershi_develop.service

import android.content.Context
import android.content.Intent

class ForegroundServiceFactory (
    private val context: Context
){

    fun <T : Intent?> create(): T {
        return Intent(context, ForegroundService()::class.java) as T
    }
}