package com.example.runnershi_develop

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (grantResults.size > 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            findNavController(R.id.myNavHostFragment).navigate(
                HomeViewPagerFragmentDirections
                    .actionHomeViewPagerFragmentToMatchingGoalFragment()
            )
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }
}