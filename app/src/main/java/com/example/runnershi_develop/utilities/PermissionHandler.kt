package com.example.runnershi_develop.utilities

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionHandler {
    fun checkPermission(activity: Activity, permissions: List<String>, requestCode: Int): Boolean {
        permissions.filter{
            ActivityCompat.checkSelfPermission(
                activity,
                it
            ) != PackageManager.PERMISSION_GRANTED
        }.run {
            return when(this.isEmpty()) {
                true -> true
                false -> {
                    ActivityCompat.requestPermissions(
                        activity, this.toTypedArray(), requestCode
                    )
                    false
                }
            }
        }
    }
}