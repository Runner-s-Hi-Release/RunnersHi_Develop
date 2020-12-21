package com.example.runnershi_develop.service

import android.app.*
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.runnershi_develop.MainActivity
import com.example.runnershi_develop.R
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.data.Opponent
import com.example.runnershi_develop.data.ResponseData
import com.example.runnershi_develop.data.RunningStart
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*

class ForegroundService internal constructor() : Service(){

    private suspend fun stopMatching(){
        when (val callResult = safeApiCall {
            RequestToServer.service
                .requestStopMatching(PrefInit.prefs.getString(ACCESS_TOKEN, ""))
        }) {
            is ResultWrapper.Success -> {
                stopForegroundService()
                Log.d("Running Repository", "Success")
            }
            is HttpException -> {
                Log.d("Running Repository", "Call Result: Http Exception")
            }
            is ResultWrapper.NetworkError -> {
                Log.d("Running Repository", "Call Result: Network Error")
            }
            is ResultWrapper.GenericError -> {
                Log.d("Running Repository", "Call Result: Generic Error")
            }
        }
    }


    private suspend fun confirmRunning(intent: Intent, receiver: ResultReceiver){
        when (val callResult = safeApiCall {
            RequestToServer.service
                .requestConfirm(PrefInit.prefs.getString(ACCESS_TOKEN, ""))
        }) {
            is ResultWrapper.Success -> {
                when(callResult.value.status){
                    200 -> {
                        val bundle = Bundle()
                        bundle.putParcelable("opponent", (callResult as ResultWrapper.Success<ResponseData<Opponent>>).value.data)
                        receiver.send(Activity.RESULT_OK, bundle)
                        stopForegroundService()
                    }
                    202 -> requestRunningStart(intent, receiver)
                }
            }
            is HttpException -> {
                Log.d("Running Repository", "Call Result: Http Exception")
            }
            is ResultWrapper.NetworkError -> {
                Log.d("Running Repository", "Call Result: Network Error")
            }
            is ResultWrapper.GenericError -> {
                Log.d("Running Repository", "Call Result: Generic Error")
            }
        }
    }


    private suspend fun requestRunningStart(intent: Intent, receiver: ResultReceiver){
        when (val callResult = safeApiCall {
            RequestToServer.service
                .requestRunningFind(PrefInit.prefs.getString(ACCESS_TOKEN, ""),
                    intent.extras?.getSerializable("runningStart") as RunningStart)
        }) {
            is ResultWrapper.Success -> {
                when(callResult.value.success){
                    true -> {
                        confirmRunning(intent, receiver)
                    }
                    false -> {
                        requestRunningStart(intent, receiver)
                    }
                }
            }
            is HttpException -> {
                Log.d("Running Repository", "Call Result: Http Exception")
            }
            is ResultWrapper.NetworkError -> {
                Log.d("Running Repository", "Call Result: Network Error")
            }
            is ResultWrapper.GenericError -> {
                Log.d("Running Repository", "Call Result: Generic Error")
            }
        }
    }


    private val CHANNEL_ID = "ForegroundServiceChannel"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_START_FOREGROUND_SERVICE -> {
                createNotificationChannel()
                val pendingIntent: PendingIntent =
                    Intent(this, MainActivity::class.java).let { notificationIntent ->
                        PendingIntent.getActivity(this, 0, notificationIntent, 0)
                    }
                val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("매칭중")
                    .setSmallIcon(R.drawable.match_logo)
                    .setContentIntent(pendingIntent)
                    .setTicker("ticker test")
                    .build()
                startForeground(1, notification)

                intent.extras?.getParcelable<ResultReceiver>("receiver")?.let {
                    CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
                        requestRunningStart(intent,
                        it
                        )
                    }
                }

            }
            ACTION_STOP_FOREGROUND_SERVICE -> {
                CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
                    stopMatching()
                }
            }
        }

        return START_NOT_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }
    companion object{
        const val ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE"
        const val ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE"
    }
}