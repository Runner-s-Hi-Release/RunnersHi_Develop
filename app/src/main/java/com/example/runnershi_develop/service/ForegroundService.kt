package com.example.runnershi_develop.service

import android.app.*
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.NavDeepLinkBuilder
import com.example.runnershi_develop.MainActivity
import com.example.runnershi_develop.R
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.data.RunningStart
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*
import kotlin.concurrent.timerTask

class ForegroundService internal constructor() : Service(){

    var index = 0
    fun requestRunningStart(intent: Intent, receiver: ResultReceiver){
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {

            when (val callResult = safeApiCall {
                RequestToServer.service
                    .requestRunningStart(PrefInit.prefs.getString(ACCESS_TOKEN, ""),
                        intent.extras?.getSerializable("runningStart") as RunningStart)
            }) {
                is ResultWrapper.Success -> {
                    index += 1
                    val bundle = Bundle()
                    bundle.putBoolean("success", callResult.value.success)
                    receiver.send(Activity.RESULT_OK, bundle)
                }
                is HttpException -> {
                    Log.d("Running Repository", "Call Result: Http Exception")
                    //todo response 가 200아닐시 무엇을 할지 논의 필요
                }
                is ResultWrapper.NetworkError -> {
                    Log.d("Running Repository", "Call Result: Network Error")
                    // todo 통신 실패시 무엇을 할지 논의 필요
                }
                is ResultWrapper.GenericError -> {
                    Log.d("Running Repository", "Call Result: Generic Error")
                    //todo
                }
            }
        }
    }


    val CHANNEL_ID = "ForegroundServiceChannel"
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if(timer == null){
            timer = Timer()
        }
        when (intent.action) {
            ACTION_START_FOREGROUND_SERVICE -> {
                createNotificationChannel()
                val pendingIntent: PendingIntent =
                    Intent(this, MainActivity::class.java).let { notificationIntent ->
                        PendingIntent.getActivity(this, 0, notificationIntent, 0)
                    }
                val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("매칭중")
                    .setSmallIcon(com.example.runnershi_develop.R.drawable.match_logo)
                    .setContentIntent(pendingIntent)
                    .setTicker("ticker test")
                    .build()
                startForeground(1, notification)


                timer?.schedule(timerTask {
                    intent.extras?.getParcelable<ResultReceiver>("receiver")?.let {
                        requestRunningStart(intent,
                            it
                        )
                    }
                }, 0, 1000)


            }
            ACTION_STOP_FOREGROUND_SERVICE -> {
                timer?.cancel()
                timer?.purge()
                timer = null
                stopForegroundService()
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
        var timer: Timer? = null
    }
}